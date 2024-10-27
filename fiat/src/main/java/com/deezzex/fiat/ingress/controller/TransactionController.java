package com.deezzex.fiat.ingress.controller;

import com.deezzex.fiat.dto.transaction.CreateTransationDto;
import com.deezzex.fiat.dto.transaction.GetTransactionDto;
import com.deezzex.fiat.entity.Transaction;
import com.deezzex.fiat.mapper.TransactionMapper;
import com.deezzex.fiat.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @GetMapping("/{transactionId}")
    public ResponseEntity<GetTransactionDto> getTransaction(@PathVariable Integer transactionId) {
        Transaction transaction = transactionService.getTransaction(transactionId);
        GetTransactionDto transactionDto = transactionMapper.toGetTransactionDto(transaction);

        return ResponseEntity.ok(transactionDto);
    }

    @PostMapping
    public ResponseEntity<GetTransactionDto> createTransaction(@RequestBody CreateTransationDto createTransationDto) {
        Transaction transaction = transactionService.createTransaction(createTransationDto);
        GetTransactionDto transactionDto = transactionMapper.toGetTransactionDto(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDto);
    }
}
