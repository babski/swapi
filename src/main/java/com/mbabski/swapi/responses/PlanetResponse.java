package com.mbabski.swapi.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlanetResponse extends Response {
    List<PlanetResults> results;
}
