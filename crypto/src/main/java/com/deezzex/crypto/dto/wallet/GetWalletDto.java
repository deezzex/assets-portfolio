package com.deezzex.crypto.dto.wallet;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.math.BigDecimal;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetWalletDto {
    Integer userId;

    BigDecimal balance;

    String blockchain;

    String address;

    String token;

    String createdAt;
}
