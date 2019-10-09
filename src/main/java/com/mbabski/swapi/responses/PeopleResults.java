package com.mbabski.swapi.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PeopleResults implements Serializable {
    String name;
    String height;
    String mass;
    String hair_color;
    String skin_color;
    String eye_color;
    String birth_year;
    String gender;
    String homeworld;
    List<String> films;
    List<String> species;
    List<String> vehicles;
    List<String> starships;
    String created;
    String edited;
    String url;

    private static final long serialVersionUID = 5423062559191461799L;
}
