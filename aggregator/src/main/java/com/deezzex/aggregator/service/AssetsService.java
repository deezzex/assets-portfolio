package com.deezzex.aggregator.service;

import com.deezzex.aggregator.client.CryptoServiceClient;
import com.deezzex.aggregator.client.FiatServiceClient;
import com.deezzex.aggregator.dto.GetUserAssetsDto;
import com.deezzex.shared.dto.GetAccountDto;
import com.deezzex.shared.dto.GetWalletDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssetsService {

    private final FiatServiceClient fiatServiceClient;
    private final CryptoServiceClient cryptoServiceClient;

    public GetUserAssetsDto getUserAssets(Integer userId) {
        List<GetAccountDto> accounts = fiatServiceClient.getAccountsByUserId(userId);
        List<GetWalletDto> wallets = cryptoServiceClient.getWalletsByUserId(userId);

        return new GetUserAssetsDto(userId, wallets, accounts);
    }
}
