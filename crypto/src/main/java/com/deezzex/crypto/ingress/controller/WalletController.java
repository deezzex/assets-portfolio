package com.deezzex.crypto.ingress.controller;

import com.deezzex.crypto.dto.wallet.CreateWalletDto;
import com.deezzex.crypto.dto.wallet.GetWalletDto;
import com.deezzex.crypto.entity.Wallet;
import com.deezzex.crypto.mapper.WalletMapper;
import com.deezzex.crypto.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    private final WalletMapper walletMapper;

    @GetMapping("/{walletId}")
    public ResponseEntity<GetWalletDto> getWallet(@PathVariable Integer walletId) {
        Wallet wallet = walletService.getWallet(walletId);
        GetWalletDto getWalletDto = walletMapper.toGetWalletDto(wallet);

        return ResponseEntity.ok(getWalletDto);
    }

    @PostMapping
    public ResponseEntity<GetWalletDto> createWallet(@RequestBody CreateWalletDto createWalletDto) {
        Wallet wallet = walletService.createWallet(createWalletDto);
        GetWalletDto getWalletDto = walletMapper.toGetWalletDto(wallet);

        return ResponseEntity.status(HttpStatus.CREATED).body(getWalletDto);
    }
}
