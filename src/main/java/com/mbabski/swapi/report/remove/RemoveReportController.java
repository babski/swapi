package com.mbabski.swapi.report.remove;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;

@RestController
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequestMapping("/report")
@RequiredArgsConstructor
class RemoveReportController {

    ReportRemover reportRemover;

    @DeleteMapping("/{report_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("report_id") final Long reportId) {
        reportRemover.delete(reportId);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        reportRemover.deleteAll();
    }
}
