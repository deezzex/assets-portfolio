package com.deezzex.crypto.ingress.controller;

import com.deezzex.crypto.dto.transaction.CreateTransactionDto;
import com.deezzex.crypto.dto.transaction.GetTransactionDto;
import com.deezzex.crypto.entity.Transaction;
import com.deezzex.crypto.mapper.TransactionMapper;
import com.deezzex.crypto.service.TransactionService;
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
        GetTransactionDto getTransactionDto = transactionMapper.toGetTransactionDto(transaction);

        return ResponseEntity.ok(getTransactionDto);
    }

    @PostMapping
    public ResponseEntity<GetTransactionDto> createTransaction(@RequestBody CreateTransactionDto createTransactionDto) {
        Transaction transaction = transactionService.createTransaction(createTransactionDto);
        GetTransactionDto getTransactionDto = transactionMapper.toGetTransactionDto(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(getTransactionDto);
    }
}
