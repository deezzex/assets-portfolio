package com.deezzex.fiat.service;

import com.deezzex.fiat.dto.account.CreateAccountDto;
import com.deezzex.fiat.entity.Account;
import com.deezzex.fiat.exception.DataNotFoundException;
import com.deezzex.fiat.model.Currency;
import com.deezzex.fiat.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account getAccount(Integer accountId) {
        log.info("Getting account by id: {}", accountId);

        return accountRepository.findById(accountId).orElseThrow(() -> new DataNotFoundException("Account", accountId));
    }

    public Account createAccount(CreateAccountDto createAccountDto) {
        log.info("Creating account: {}", createAccountDto);

        var account = buildAccount(createAccountDto);

        log.info("Account successfully created: {}", account);

        return accountRepository.save(account);
    }

    private static Account buildAccount(CreateAccountDto createAccountDto) {
        return Account.builder()
                .userId(createAccountDto.getUserId())
                .name(createAccountDto.getName())
                .balance(createAccountDto.getBalance())
                .currency(Currency.getCurrency(createAccountDto.getCurrency()))
                .createdAt(Instant.now())
                .build();
    }
}
