package com.mbabski.swapi.repository;

import com.mbabski.swapi.domain.Report;

import java.util.List;
import java.util.Optional;

public interface ReportRepository {
    Report create(final Report report);
    Report update(final Report report);
    Optional<Report> read(final Long id);
    List<Report> readAll();
    void delete(final Long id);
    void deleteAll();
}
