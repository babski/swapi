package com.mbabski.swapi.report.save;

import com.mbabski.swapi.domain.Report;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
class ReportGenerator {
    FilmsDatabase filmsDatabase;
    ReportPersister reportPersister;

    void generateReport(ReportCriteriaDto dto) {
        List<Report> matchedFilms = filmsDatabase.findMatchingFilms(dto.getQueryCriteriaCharacterPhrase(), dto.getQueryCriteriaPlanetName());
        reportPersister.saveReports(dto.getReportId(), matchedFilms);
    }

}
