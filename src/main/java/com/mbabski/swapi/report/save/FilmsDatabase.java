package com.mbabski.swapi.report.save;

import com.mbabski.swapi.domain.Report;
import com.mbabski.swapi.exception.CharacterPhraseNotFoundException;
import com.mbabski.swapi.exception.PlanetPhraseNotFoundException;
import com.mbabski.swapi.responses.FilmResponse;
import com.mbabski.swapi.responses.PeopleResponse;
import com.mbabski.swapi.responses.PeopleResults;
import com.mbabski.swapi.responses.PlanetResponse;
import com.mbabski.swapi.responses.PlanetResults;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class FilmsDatabase {

    private final ApiConnector apiConnector;

    private static final String peopleApiUrl = "https://swapi.co/api/people/";
    private static final String planetApiUrl = "https://swapi.co/api/planets/";

    List<Report> findMatchingFilms(String queryCriteriaCharacterPhrase, String queryCriteriaPlanetName) {
        final PeopleResponse[] peopleResponse = {apiConnector.getSwapiResponse(buildQueryString(peopleApiUrl, queryCriteriaCharacterPhrase), PeopleResponse.class)};
        final PlanetResponse[] planetResponse = {apiConnector.getSwapiResponse(buildQueryString(planetApiUrl, queryCriteriaPlanetName), PlanetResponse.class)};

        if (peopleResponse[0].getCount() == 0) {
            throw new CharacterPhraseNotFoundException("Queried character phrase '" + queryCriteriaCharacterPhrase + "' returned no results");
        }
        if (planetResponse[0].getCount() == 0)
            throw new PlanetPhraseNotFoundException("Queried planet phrase '" + queryCriteriaPlanetName + "' returned no results");

        List<PeopleResults> peopleResultsList = peopleResponse[0].getResults();
        List<PlanetResults> planetResultsList = planetResponse[0].getResults();
        CompletableFuture<List<PeopleResults>> peopleList = getListCompletableFuture(peopleResponse, peopleResultsList);
        CompletableFuture<List<PlanetResults>> planetList = getListCompletableFuture(planetResponse, planetResultsList);
        List<PeopleResults> peopleResults = getResult(peopleList);
        List<PlanetResults> planetResults = getResult(planetList);
        List<String> planetUrls = getPlanetUrlsFromResults(planetResults);
        List<String> filmUrlsToDownload = getFilmUrlsToDownload(peopleResults, planetUrls);
        Map<String, String> filmsMap = getFilmsMap(filmUrlsToDownload);
        List<Report> reports = new ArrayList<>();
        peopleResults.stream()
                .filter(peopleResult -> planetUrls.contains(peopleResult.getHomeworld()))
                .forEach(peopleResult -> {
                    peopleResult.getFilms().forEach(filmUrl -> {
                        reports.add(getCreatedReport(planetResults, filmsMap, peopleResult, filmUrl, queryCriteriaCharacterPhrase, queryCriteriaPlanetName));
                    });
                });
        return reports;
    }

    private List<String> getPlanetUrlsFromResults(List<PlanetResults> planetResults) {
        return planetResults.stream()
                    .map(PlanetResults::getUrl)
                    .collect(Collectors.toList());
    }

    private <T> List<T> getResult(CompletableFuture<List<T>> elements) {
        try {
            return elements.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Can't get result from online database");
    }

    private CompletableFuture<List<PlanetResults>> getListCompletableFuture(PlanetResponse[] planetResponse, List<PlanetResults> planetResultsList) {
        return CompletableFuture.supplyAsync(() -> {
            while (Objects.nonNull(planetResponse[0].getNext())) {
                planetResponse[0] = apiConnector.getSwapiResponse(planetResponse[0].getNext(), PlanetResponse.class);
                planetResultsList.addAll(planetResponse[0].getResults());
            }
            return planetResultsList;
        });
    }

    private CompletableFuture<List<PeopleResults>> getListCompletableFuture(PeopleResponse[] peopleResponse, List<PeopleResults> peopleResultsList) {
        return CompletableFuture.supplyAsync(() -> {
            while (Objects.nonNull(peopleResponse[0].getNext())) {
                peopleResponse[0] = apiConnector.getSwapiResponse(peopleResponse[0].getNext(), PeopleResponse.class);
                peopleResultsList.addAll(peopleResponse[0].getResults());
            }
            return peopleResultsList;
        });
    }

    private String buildQueryString(String ApiUrl, String queryCriteria) {
        return UriComponentsBuilder.fromUriString(ApiUrl).queryParam("search", queryCriteria).toUriString();
    }

    private Map<String, String> getFilmsMap(List<String> filmUrlsToDownload) {
        Map<String, String> filmsMap = new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(filmUrlsToDownload.size());
        for (String filmUrl : filmUrlsToDownload) {
            executorService.execute(() -> filmsMap.put(filmUrl, apiConnector.getSwapiResponse(filmUrl, FilmResponse.class).getTitle())
            );
        }
        awaitTerminationAfterShutdown(executorService);
        return filmsMap;
    }

    private void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private Report getCreatedReport(List<PlanetResults> planetResults,
                                    Map<String, String> filmsMap,
                                    PeopleResults peopleResult,
                                    String filmUrl,
                                    String queryCriteriaCharacterPhrase,
                                    String queryCriteriaPlanetName) {
        return Report.builder()
                .queryCriteriaCharacterPhrase(queryCriteriaCharacterPhrase)
                .queryCriteriaPlanetName(queryCriteriaPlanetName)
                .characterId(Integer.valueOf(peopleResult.getUrl().replaceAll("[^0-9]", "")))
                .characterName(peopleResult.getName())
                .planetId(Integer.valueOf(peopleResult.getHomeworld().replaceAll("[^0-9]", "")))
                .planetName(getPlanetNameByUrl(planetResults, peopleResult.getHomeworld()))
                .filmId(Integer.valueOf(filmUrl.replaceAll("[^0-9]", "")))
                .filmName(filmsMap.get(filmUrl))
                .build();
    }

    private List<String> getFilmUrlsToDownload(List<PeopleResults> peopleResults, List<String> planetUrls) {
        return peopleResults.stream()
                .filter(peopleResult -> planetUrls.contains(peopleResult.getHomeworld()))
                .map(PeopleResults::getFilms)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    private String getPlanetNameByUrl(List<PlanetResults> planetResults, String url) {
        return planetResults.stream()
                .filter(planetResult -> planetResult.getUrl().equals(url))
                .map(PlanetResults::getName)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No planet name found at " + url));
    }
}
