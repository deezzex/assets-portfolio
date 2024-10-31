package com.deezzex.crypto.ingress.controller;

import com.deezzex.crypto.dto.WalletState;
import com.deezzex.crypto.dto.wallet.CreateWalletDto;
import com.deezzex.crypto.entity.Wallet;
import com.deezzex.crypto.mapper.WalletMapper;
import com.deezzex.crypto.service.WalletService;
import com.deezzex.shared.dto.GetWalletDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

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

    @GetMapping(value = "/state/{walletId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<WalletState> getWalletBalanceState(@PathVariable Integer walletId) {
        Wallet wallet = walletService.getWallet(walletId);
        return walletService.getWalletState(wallet);
    }

    @GetMapping
    public ResponseEntity<List<GetWalletDto>> getWalletsByUserId(@RequestParam Integer userId) {
        List<Wallet> wallets = walletService.getWalletsByUserId(userId);

        List<GetWalletDto> walletDtoList = wallets.stream().map(walletMapper::toGetWalletDto).toList();

        return ResponseEntity.ok(walletDtoList);
    }

    @PostMapping
    public ResponseEntity<GetWalletDto> createWallet(@RequestBody CreateWalletDto createWalletDto) {
        Wallet wallet = walletService.createWallet(createWalletDto);
        GetWalletDto getWalletDto = walletMapper.toGetWalletDto(wallet);

        return ResponseEntity.status(HttpStatus.CREATED).body(getWalletDto);
    }
}
