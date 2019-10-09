package com.mbabski.swapi.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenericErrorDto extends ErrorDto {
    private GenericErrorMessageDto message;

    @Builder
    public GenericErrorDto(final LocalDateTime timestamp, final Integer status,
                           final String error, final String path,
                           final GenericErrorMessageDto message) {
        super(timestamp, status, error, path);
        this.message = message;
    }
}
