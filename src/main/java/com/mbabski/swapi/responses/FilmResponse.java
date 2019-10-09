package com.mbabski.swapi.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmResponse {
     String title;
     int episode_id;
     String opening_crawl;
     String director;
     String producer;
     LocalDate release_date;
     List<String> characters;
     List<String> planets;
     List<String> starships;
     List<String> vehicles;
     List<String> species;
     String created;
     String edited;
     String url;
}