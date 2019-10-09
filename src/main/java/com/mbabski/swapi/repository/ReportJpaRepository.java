package com.mbabski.swapi.repository;

import com.mbabski.swapi.persistence.ReportJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportJpaRepository extends JpaRepository<ReportJpaEntity, Long> {
}
