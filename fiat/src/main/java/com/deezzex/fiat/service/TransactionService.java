package com.deezzex.fiat.service;

import com.deezzex.fiat.dto.transaction.CreateTransationDto;
import com.deezzex.fiat.entity.Account;
import com.deezzex.fiat.entity.Transaction;
import com.deezzex.fiat.exception.DataNotFoundException;
import com.deezzex.fiat.model.DebitCreditIndicator;
import com.deezzex.fiat.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public Transaction getTransaction(Integer transactionId) {
        log.info("Getting transaction by id: {}", transactionId);

        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new DataNotFoundException("Transaction", transactionId));
    }

    public Transaction createTransaction(CreateTransationDto createTransationDto) {
        log.info("Creating transaction: {}", createTransationDto);

        Account account = accountService.getAccount(createTransationDto.getAccountId());

        DebitCreditIndicator indicator = DebitCreditIndicator.getIndicator(createTransationDto.getDebitCreditIndicator());

        BigDecimal balanceAfter = calculateBalanceAfter(account.getBalance(), createTransationDto.getAmount(), indicator);

        var transaction = buildTransaction(createTransationDto, account, balanceAfter, indicator);

        account.setBalance(balanceAfter);

        transaction = transactionRepository.save(transaction);

        log.info("Transaction successfully created: {}", transaction);

        return transaction;
    }

    private Transaction buildTransaction(CreateTransationDto createTransationDto,
                                         Account account,
                                         BigDecimal balanceAfter,
                                         DebitCreditIndicator indicator) {
        return Transaction.builder()
                .account(account)
                .amount(createTransationDto.getAmount())
                .balanceBefore(account.getBalance())
                .balanceAfter(balanceAfter)
                .description(createTransationDto.getDescription())
                .debitCreditIndicator(indicator)
                .createdAt(Instant.now())
                .build();
    }

    private BigDecimal calculateBalanceAfter(BigDecimal balanceBefore,
                                             BigDecimal amount,
                                             DebitCreditIndicator debitCreditIndicator) {
        return switch (debitCreditIndicator) {
            case D -> balanceBefore.subtract(amount);
            case C -> balanceBefore.add(amount);
        };
    }
}
