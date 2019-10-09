package com.mbabski.swapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class QueryDto {
    @NotBlank
    String queryCriteriaCharacterPhrase;
    @NotBlank
    String queryCriteriaPlanetName;
}
