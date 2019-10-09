package com.mbabski.swapi.repository;

import com.mbabski.swapi.domain.Report;
import com.mbabski.swapi.exception.ReportNotFoundException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportRepositoryImpl implements ReportRepository {

    @NonNull
    ReportJpaRepository reportJpaRepository;

    @Override
    public Report create(Report report) {
        return ReportMapper.fromEntity(reportJpaRepository.save(ReportMapper.toEntity(report)));
    }

    @Override
    public Report update(Report report) {
        return reportJpaRepository.findById(report.getReportId())
                .map(entity -> {
                    entity.mergeWith(ReportMapper.toEntity(report));
                    return entity;
                })
                .map(reportJpaRepository::save)
                .map(ReportMapper::fromEntity)
                .orElseThrow(() -> new ReportNotFoundException(report.getReportId()));
    }

    @Override
    public Optional<Report> read(Long id) {
        return reportJpaRepository.findById(id).map(ReportMapper::fromEntity);
    }

    @Override
    public List<Report> readAll() {
        return reportJpaRepository.findAll()
                .stream()
                .map(ReportMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        reportJpaRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        reportJpaRepository.deleteAll();
    }
}
