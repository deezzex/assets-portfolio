package com.deezzex.crypto.service;

import com.deezzex.crypto.dto.WalletState;
import com.deezzex.crypto.dto.wallet.CreateWalletDto;
import com.deezzex.crypto.entity.Wallet;
import com.deezzex.crypto.model.Blockchain;
import com.deezzex.crypto.model.Token;
import com.deezzex.crypto.repository.WalletRepository;
import com.deezzex.shared.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final PriceService priceService;

    public Wallet getWallet(Integer walletId) {
        log.info("Getting wallet by id: {}", walletId);

        return walletRepository.findById(walletId).orElseThrow(() -> new DataNotFoundException("Wallet", walletId));
    }

    public List<Wallet> getWalletsByUserId(Integer userId) {
        log.info("Getting wallets by userId: {}", userId);

        return walletRepository.findByUserId(userId);
    }

    public Wallet createWallet(CreateWalletDto createWalletDto) {
        log.info("Creating wallet: {}", createWalletDto);

        var wallet = buildWallet(createWalletDto);

        wallet = walletRepository.save(wallet);

        log.info("Wallet successfully created: {}", wallet);

        return wallet;
    }

    public Flux<WalletState> getWalletState(Wallet wallet) {
        return priceService.generatePrices()
                .map(prices -> {
                    BigDecimal priceInUsd = prices.getOrDefault(wallet.getToken(), BigDecimal.ZERO);
                    BigDecimal balanceInUsd = wallet.getBalance().multiply(priceInUsd);
                    return new WalletState(wallet.getToken(), wallet.getBalance(), balanceInUsd);
                });
    }

    private Wallet buildWallet(CreateWalletDto createWalletDto) {
        return Wallet.builder()
                .userId(createWalletDto.getUserId())
                .balance(BigDecimal.ZERO)
                .blockchain(Blockchain.getFromString(createWalletDto.getBlockchain()))
                .token(Token.getFromString(createWalletDto.getToken()))
                .address(createWalletDto.getAddress())
                .createdAt(Instant.now())
                .build();
    }
}
