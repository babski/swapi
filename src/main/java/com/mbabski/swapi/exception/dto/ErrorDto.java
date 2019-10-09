package com.mbabski.swapi.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public abstract class ErrorDto {

    LocalDateTime timestamp;
    Integer status;
    String error;
    String path;
}
