package com.mbabski.swapi.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportNotFoundErrorDto extends ErrorDto {
    private ReportNotFoundMessageDto message;

    @Builder
    public ReportNotFoundErrorDto(final LocalDateTime timestamp, final Integer status,
                                  final String error, final String path,
                                  final ReportNotFoundMessageDto message) {
        super(timestamp, status, error, path);
        this.message = message;
    }
}
