package com.mbabski.swapi.exception;

import com.mbabski.swapi.exception.dto.GenericErrorMessageDto;
import com.mbabski.swapi.exception.dto.ReportNotFoundMessageDto;

import java.util.Optional;

class ErrorMessageFactory {

    private ErrorMessageFactory() {
        throw new IllegalStateException("Utility class");
    }

    static GenericErrorMessageDto createErrorMessage(final Exception exception) {
        return GenericErrorMessageDto.builder()
                .errorDescription(getErrorDescription(exception))
                .build();
    }

    static ReportNotFoundMessageDto createErrorMessage(final ReportNotFoundException exception) {
        return ReportNotFoundMessageDto.builder()
                .reportId(exception.getReportId())
                .message("Report not found")
                .build();
    }

    private static String getErrorDescription(final Exception exception) {
        return Optional.ofNullable(exception.getMessage())
                .orElse("No message available");
    }
}
