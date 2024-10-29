package com.deezzex.crypto.dto.transaction;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.math.BigDecimal;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetTransactionDto {

    Integer id;

    Integer fromWalletId;

    Integer toWalletId;

    BigDecimal amount;

    BigDecimal fee;

    String hash;

    String status;

    String createdAt;
}
