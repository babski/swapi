package com.mbabski.swapi.exception;

import lombok.Getter;

@Getter
public class ReportNotFoundException extends RuntimeException {
    private final String reportId;

    public ReportNotFoundException(Long reportId) {
        this.reportId = reportId.toString();
    }
}
