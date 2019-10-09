package com.mbabski.swapi.repository;

import com.mbabski.swapi.domain.Report;
import com.mbabski.swapi.dto.ReportDto;
import com.mbabski.swapi.persistence.ReportJpaEntity;

public class ReportMapper {
    public static Report fromEntity(ReportJpaEntity entity) {
        return Report.builder()
                .reportId(entity.getReportId())
                .queryCriteriaCharacterPhrase(entity.getQueryCriteriaCharacterPhrase())
                .queryCriteriaPlanetName(entity.getQueryCriteriaPlanetName())
                .filmId(entity.getFilmId())
                .filmName(entity.getFilmName())
                .characterId(entity.getCharacterId())
                .characterName(entity.getCharacterName())
                .planetId(entity.getPlanetId())
                .planetName(entity.getPlanetName())
                .build();
    }

    public static ReportJpaEntity toEntity(Report report) {
        return ReportJpaEntity.builder()
                .reportId(report.getReportId())
                .queryCriteriaCharacterPhrase(report.getQueryCriteriaCharacterPhrase())
                .queryCriteriaPlanetName(report.getQueryCriteriaPlanetName())
                .filmId(report.getFilmId())
                .filmName(report.getFilmName())
                .characterId(report.getCharacterId())
                .characterName(report.getCharacterName())
                .planetId(report.getPlanetId())
                .planetName(report.getPlanetName())
                .build();
    }

    public static ReportDto toDto(Report report) {
        return ReportDto.builder()
                .reportId(report.getReportId())
                .queryCriteriaCharacterPhrase(report.getQueryCriteriaCharacterPhrase())
                .queryCriteriaPlanetName(report.getQueryCriteriaPlanetName())
                .filmId(report.getFilmId())
                .filmName(report.getFilmName())
                .characterId(report.getCharacterId())
                .characterName(report.getCharacterName())
                .planetId(report.getPlanetId())
                .planetName(report.getPlanetName())
                .build();
    }
}
