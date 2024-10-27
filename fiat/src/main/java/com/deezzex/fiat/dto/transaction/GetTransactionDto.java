package com.deezzex.fiat.dto.transaction;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.math.BigDecimal;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetTransactionDto {

    Integer id;

    Integer accountId;

    BigDecimal amount;

    BigDecimal balanceBefore;

    BigDecimal balanceAfter;

    Character debitCreditIndicator;

    String description;

    String createdAt;
}
