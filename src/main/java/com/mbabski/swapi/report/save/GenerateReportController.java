package com.mbabski.swapi.report.save;

import com.mbabski.swapi.dto.QueryDto;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.OK;

@RestController
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequestMapping("/report")
@RequiredArgsConstructor
class GenerateReportController {

    ReportGenerator reportGenerator;

    @PutMapping("/{report_id}")
    @ResponseStatus(OK)
    public void create(@PathVariable("report_id") Long reportId, @RequestBody @Valid QueryDto queryDto) {
        reportGenerator.generateReport(ReportCriteriaDto.of(reportId, queryDto));
    }
}
