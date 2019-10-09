package com.mbabski.swapi.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlanetResults {
    String name;
    String rotation_period;
    String orbital_period;
    String diameter;
    String climate;
    String gravity;
    String terrain;
    String surface_water;
    String population;
    List<String> residents;
    List<String> films;
    String created;
    String edited;
    String url;
}
