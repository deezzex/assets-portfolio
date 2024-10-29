package com.deezzex.crypto.dto.transaction;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.math.BigDecimal;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateTransactionDto {

    Integer fromWalletId;

    Integer toWalletId;

    BigDecimal amount;

    BigDecimal fee;
}
