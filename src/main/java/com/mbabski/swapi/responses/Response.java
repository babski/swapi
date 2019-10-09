package com.mbabski.swapi.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Response {
    int count;
    String next;
    String previous;
}
