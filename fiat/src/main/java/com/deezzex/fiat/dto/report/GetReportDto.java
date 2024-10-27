package com.deezzex.fiat.dto.report;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.math.BigDecimal;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetReportDto {

    Integer id;

    Integer accountId;

    BigDecimal totalCreditAmount;

    BigDecimal totalDebitAmount;

    String createdAt;

    String startDate;

    String endDate;
}
