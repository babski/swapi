package com.mbabski.swapi.exception;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ValidationError {
    String field;
    String className;
    String error;
}
