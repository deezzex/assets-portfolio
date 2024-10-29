package com.deezzex.crypto.service;

import com.deezzex.crypto.dto.transaction.CreateTransactionDto;
import com.deezzex.crypto.entity.Transaction;
import com.deezzex.crypto.entity.Wallet;
import com.deezzex.crypto.exception.DataNotFoundException;
import com.deezzex.crypto.repository.TransactionRepository;
import com.deezzex.crypto.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public Transaction getTransaction(Integer transactionId) {
        log.info("Getting transaction by id: {}", transactionId);

        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new DataNotFoundException("Transaction", transactionId));
    }

    public Transaction createTransaction(CreateTransactionDto createTransactionDto) {
        log.info("Creating transaction: {}", createTransactionDto);

        Optional<Wallet> fromWalletOptional = Optional.empty();
        Optional<Wallet> toWalletOptional = Optional.empty();

        if (Objects.nonNull(createTransactionDto.getFromWalletId())) {
            fromWalletOptional = walletRepository.findById(createTransactionDto.getFromWalletId());
        }
        if (Objects.nonNull(createTransactionDto.getToWalletId())) {
            toWalletOptional = walletRepository.findById(createTransactionDto.getToWalletId());
        }

        var transaction = buildTransaction(createTransactionDto, fromWalletOptional, toWalletOptional);

        transaction = transactionRepository.save(transaction);

        log.info("Successfully created transaction: {}", transaction);

        updateWallets(fromWalletOptional, toWalletOptional, createTransactionDto.getAmount(), createTransactionDto.getFee());

        return transaction;
    }

    private void updateWallets(Optional<Wallet> fromWalletOptional,
                               Optional<Wallet> toWalletOptional,
                               BigDecimal trxAmount,
                               BigDecimal trxFee) {
        if (fromWalletOptional.isPresent()) {
            Wallet fromWallet = fromWalletOptional.get();
            fromWallet.setBalance(fromWallet.getBalance().subtract(trxAmount));

            fromWallet = walletRepository.save(fromWallet);

            log.info("Updated fromWallet: {}", fromWallet);
        }
        if (toWalletOptional.isPresent()) {
            Wallet toWallet = toWalletOptional.get();
            toWallet.setBalance(toWallet.getBalance().add(trxAmount.subtract(trxFee)));

            toWallet = walletRepository.save(toWallet);

            log.info("Updated toWallet: {}", toWallet);
        }
    }

    private Transaction buildTransaction(CreateTransactionDto createTransactionDto,
                                         Optional<Wallet> fromWalletOptional,
                                         Optional<Wallet> toWalletOptional) {
        return Transaction.builder()
                .fromWallet(fromWalletOptional.orElse(null))
                .toWallet(toWalletOptional.orElse(null))
                .hash(UUID.randomUUID().toString())
                .amount(createTransactionDto.getAmount())
                .fee(createTransactionDto.getFee())
                .createdAt(Instant.now())
                .status("A")
                .build();
    }
}
