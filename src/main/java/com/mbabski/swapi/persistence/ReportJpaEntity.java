package com.mbabski.swapi.persistence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@Data
@Table(name = "reports")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportJpaEntity {
    @Id
    @GeneratedValue
    Long reportId;
    String queryCriteriaCharacterPhrase;
    String queryCriteriaPlanetName;
    Integer filmId;
    String filmName;
    Integer characterId;
    String characterName;
    Integer planetId;
    String planetName;

    public void mergeWith(ReportJpaEntity entity){
        this.queryCriteriaCharacterPhrase = entity.queryCriteriaCharacterPhrase;
        this.queryCriteriaPlanetName = entity.queryCriteriaPlanetName;
        this.filmId = entity.filmId;
        this.filmName = entity.filmName;
        this.characterId = entity.characterId;
        this.characterName = entity.characterName;
        this.planetId = entity.planetId;
        this.planetName = entity.planetName;
    }
}
