package com.deezzex.aggregator.dto;

import com.deezzex.shared.dto.GetAccountDto;
import com.deezzex.shared.dto.GetWalletDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.util.List;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetUserAssetsDto {

    Integer userId;

    List<GetWalletDto> wallets;

    List<GetAccountDto> accounts;
}
