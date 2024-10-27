package com.deezzex.fiat.ingress.controller;

import com.deezzex.fiat.dto.account.CreateAccountDto;
import com.deezzex.fiat.dto.account.GetAccountDto;
import com.deezzex.fiat.entity.Account;
import com.deezzex.fiat.mapper.AccountMapper;
import com.deezzex.fiat.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @GetMapping("/{accountId}")
    public ResponseEntity<GetAccountDto> getAccount(@PathVariable Integer accountId) {
        Account account = accountService.getAccount(accountId);
        GetAccountDto dto = accountMapper.toGetAccountDto(account);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<GetAccountDto> createAccount(@RequestBody CreateAccountDto createAccountDto) {
        Account account = accountService.createAccount(createAccountDto);
        GetAccountDto dto = accountMapper.toGetAccountDto(account);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
