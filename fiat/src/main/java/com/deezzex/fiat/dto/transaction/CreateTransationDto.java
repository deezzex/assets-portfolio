package com.deezzex.fiat.dto.transaction;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.math.BigDecimal;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateTransationDto {

    Integer accountId;

    BigDecimal amount;

    Character debitCreditIndicator;

    String description;
}
