package com.mbabski.swapi.exception;

import com.mbabski.swapi.exception.dto.GenericErrorDto;
import com.mbabski.swapi.exception.dto.GenericErrorMessageDto;
import com.mbabski.swapi.exception.dto.MethodArgumentNotValidErrorDto;
import com.mbabski.swapi.exception.dto.ReportNotFoundErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

class ErrorDtoFactory {

    private ErrorDtoFactory() {
    }

    static GenericErrorDto createErrorDto(final Exception exception,
                                          final WebRequest webRequest,
                                          final HttpStatus status) {
        return GenericErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.name())
                .message(ErrorMessageFactory.createErrorMessage(exception))
                .path(webRequest.getContextPath())
                .build();
    }

    static MethodArgumentNotValidErrorDto createErrorDto(final MethodArgumentNotValidException exception,
                                                         final WebRequest webRequest,
                                                         final HttpStatus status) {
        return MethodArgumentNotValidErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.name())
                .validationErrors(fromBindingResult(exception.getBindingResult()))
                .path(webRequest.getContextPath())
                .build();
    }

    static GenericErrorDto createErrorDto(final IOException exception,
                                          final WebRequest webRequest,
                                          final HttpStatus status) {
        return GenericErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.name())
                .message(GenericErrorMessageDto.builder()
                        .errorDescription("Failed to upload file")
                        .exceptionName(exception.getClass().getSimpleName())
                        .build())
                .path(webRequest.getContextPath())
                .build();
    }

    static ReportNotFoundErrorDto createErrorDto(final ReportNotFoundException exception,
                                                 final WebRequest webRequest,
                                                 final HttpStatus status) {
        return ReportNotFoundErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.name())
                .message(ErrorMessageFactory.createErrorMessage(exception))
                .path(webRequest.getContextPath())
                .build();
    }

    private static List<ValidationError> fromBindingResult(BindingResult bindingResult) {
        List<ValidationError> fieldErrors = bindingResult.getFieldErrors().stream()
                .map(fe -> new ValidationError(fe.getField(), fe.getObjectName(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        fieldErrors.addAll(bindingResult.getGlobalErrors().stream()
                .map(fe -> new ValidationError(null, fe.getObjectName(), fe.getDefaultMessage()))
                .collect(Collectors.toList()));
        return fieldErrors;
    }

}
