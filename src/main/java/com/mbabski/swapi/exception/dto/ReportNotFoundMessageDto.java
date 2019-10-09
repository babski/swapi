package com.mbabski.swapi.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ReportNotFoundMessageDto {
    String reportId;
    String message;
}
