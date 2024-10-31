package com.deezzex.shared.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.math.BigDecimal;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetAccountDto {

    Integer id;

    Integer userId;

    BigDecimal balance;

    String name;

    String currency;

    String createdAt;
}
