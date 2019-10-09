package com.mbabski.swapi.exception.dto;

import com.mbabski.swapi.exception.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MethodArgumentNotValidErrorDto extends ErrorDto {
    private List<ValidationError> validationErrors;

    @Builder
    public MethodArgumentNotValidErrorDto(final LocalDateTime timestamp, final Integer status,
                                          final String error, final String path, List<ValidationError> validationErrors) {
        super(timestamp, status, error, path);
        this.validationErrors = validationErrors;
    }
}
