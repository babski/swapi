package com.mbabski.swapi.exception;

import com.mbabski.swapi.exception.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ErrorDto> handleGenericException(final Exception e, final WebRequest request) {
        log.error(e.getClass().getSimpleName(), e);
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(ErrorDtoFactory.createErrorDto(e, request, status), status);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<ErrorDto> handleDomainObjectNotFoundException(final ReportNotFoundException e,
                                                                        final WebRequest request) {
        log.error(e.getClass().getSimpleName(), e);
        final HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(ErrorDtoFactory.createErrorDto(e, request, status), status);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PlanetPhraseNotFoundException.class)
    public ResponseEntity<ErrorDto> handlePlanetPhraseNotFoundException(final PlanetPhraseNotFoundException e,
                                                                        final WebRequest request) {
        log.error(e.getClass().getSimpleName(), e);
        final HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(ErrorDtoFactory.createErrorDto(e, request, status), status);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CharacterPhraseNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCharacterPhraseNotFoundException(final CharacterPhraseNotFoundException e,
                                                                           final WebRequest request) {
        log.error(e.getClass().getSimpleName(), e);
        final HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(ErrorDtoFactory.createErrorDto(e, request, status), status);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        log.error(e.getClass().getSimpleName(), e);
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(ErrorDtoFactory.createErrorDto(e, request, status), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
