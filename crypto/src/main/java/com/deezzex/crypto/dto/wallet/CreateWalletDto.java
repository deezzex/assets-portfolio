package com.deezzex.crypto.dto.wallet;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateWalletDto {

    Integer userId;

    String blockchain;

    String address;

    String token;
}
