package com.mbabski.swapi.report.find;

import com.mbabski.swapi.domain.Report;
import com.mbabski.swapi.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class ReportReader {
    ReportRepository reportRepository;

    Optional<Report> read(Long reportId) {
        return reportRepository.read(reportId);
    }

    List<Report> readAll() {
        return reportRepository.readAll();
    }
}
