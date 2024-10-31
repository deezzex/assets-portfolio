package com.deezzex.crypto.dto;

import com.deezzex.crypto.model.Token;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class WalletState {
    Token token;
    BigDecimal balance;
    BigDecimal balanceInUsd;
}
