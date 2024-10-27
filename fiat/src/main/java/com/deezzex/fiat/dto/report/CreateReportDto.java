package com.deezzex.fiat.dto.report;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.time.LocalDate;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateReportDto {

    Integer accountId;

    LocalDate startDate;

    LocalDate endDate;
}
