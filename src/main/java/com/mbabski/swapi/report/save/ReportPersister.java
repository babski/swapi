package com.mbabski.swapi.report.save;

import com.mbabski.swapi.domain.Report;
import com.mbabski.swapi.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class ReportPersister {
    ReportRepository reportRepository;

    void saveReports(Long reportId, List<Report> matchedMovies) {
        for (int i = 0; i < matchedMovies.size(); i++) {
            Report report = matchedMovies.get(i);
            if (isFirst(i)) {
                saveFirst(reportId, report);
            } else {
                reportRepository.create(report);
            }
        }
    }

    private void saveFirst(Long reportId, Report report) {
        reportRepository.read(reportId)
                .map(Report::getReportId)
                .ifPresent(report::setReportId);
        if (Objects.nonNull(report.getReportId()))
            reportRepository.update(report);
        else
            reportRepository.create(report);
    }

    private boolean isFirst(int i) {
        return i == 0;
    }
}
