package com.mbabski.swapi.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
public class ReportDto {
    Long reportId;
    String queryCriteriaCharacterPhrase;
    String queryCriteriaPlanetName;
    Integer filmId;
    String filmName;
    Integer characterId;
    String characterName;
    Integer planetId;
    String planetName;
}
