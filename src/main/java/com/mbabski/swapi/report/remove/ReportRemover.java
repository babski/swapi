package com.mbabski.swapi.report.remove;

import com.mbabski.swapi.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class ReportRemover {
    ReportRepository reportRepository;

    void delete(Long reportId) {
        reportRepository.delete(reportId);
    }

    void deleteAll() {
        reportRepository.deleteAll();
    }
}
