package com.mbabski.swapi.report.find;

import com.mbabski.swapi.dto.ReportDto;
import com.mbabski.swapi.exception.ReportNotFoundException;
import com.mbabski.swapi.repository.ReportMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@RestController
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequestMapping("/report")
@RequiredArgsConstructor
class ReadReportController {

    ReportReader reportReader;

    @GetMapping("/{report_id}")
    public ReportDto read(@PathVariable("report_id") final Long reportId) {
        return ReportMapper.toDto(reportReader.read(reportId).orElseThrow(() -> new ReportNotFoundException(reportId)));
    }

    @GetMapping()
    public List<ReportDto> readAll() {
        return reportReader.readAll()
                .stream()
                .map(ReportMapper::toDto)
                .collect(Collectors.toList());
    }
}
