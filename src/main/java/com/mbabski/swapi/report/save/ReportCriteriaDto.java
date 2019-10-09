package com.mbabski.swapi.report.save;

import com.mbabski.swapi.dto.QueryDto;
import lombok.Value;
import lombok.experimental.Delegate;

@Value(staticConstructor = "of")
class ReportCriteriaDto {
    Long reportId;
    @Delegate
    QueryDto dto;
}
