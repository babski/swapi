package com.mbabski.swapi.report.save;

import com.mbabski.swapi.domain.Report;
import com.mbabski.swapi.dto.QueryDto;
import com.mbabski.swapi.repository.ReportRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportGeneratorTest {
    private static final String CHARACTER_PHRASE = "R2D2";
    private static final String PLANET_NAME = "Mars";

    @MockBean
    private FilmsDatabase filmsDatabase;

    @Autowired
    private ReportGenerator reportGenerator;

    @Autowired
    private ReportRepository reportRepository;

    @Test
    @DirtiesContext
    public void shouldSaveReportBasedOnMatchedFilmNamesAndCharacters() {
        ReportCriteriaDto criteriaDto = reportCriteriaDto();
        //and
        List<Report> reports = Collections.singletonList(report());
        //and
        when(filmsDatabase.findMatchingFilms(CHARACTER_PHRASE, PLANET_NAME)).thenReturn(reports);

        //when
        reportGenerator.generateReport(criteriaDto);

        //then
        Assertions.assertThat(reportRepository.readAll()).hasSize(1);
        Assertions.assertThat(reportRepository.readAll().get(0).getCharacterName()).isEqualTo(CHARACTER_PHRASE);
        Assertions.assertThat(reportRepository.readAll().get(0).getPlanetName()).isEqualTo(PLANET_NAME);
    }

    private Report report() {
        return Report.builder().filmName("Rocky").planetName(PLANET_NAME).characterName(CHARACTER_PHRASE).build();
    }

    private ReportCriteriaDto reportCriteriaDto() {
        //given
        QueryDto dto = queryDto();
        //and
        return ReportCriteriaDto.of(1l, dto);
    }

    private QueryDto queryDto() {
        QueryDto queryDto = new QueryDto();
        queryDto.setQueryCriteriaCharacterPhrase(CHARACTER_PHRASE);
        queryDto.setQueryCriteriaPlanetName(PLANET_NAME);
        return queryDto;
    }
}