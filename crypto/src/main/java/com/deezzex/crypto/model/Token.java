package com.deezzex.crypto.model;

import com.deezzex.crypto.exception.GeneralApplicationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Token {
    BTC("BTC"), TON("TON"), TRX("TRX"), NOT("NOT"), ARB("ARB"), USDT("USDT");

    private static final Token[] VALUES = values();
    private final String name;

    public static Token getFromString(String string) {
        return Arrays.stream(VALUES)
                .filter(token -> token.getName().equals(string))
                .findAny()
                .orElseThrow(() -> new GeneralApplicationException("Unknown token: " + string));
    }
}
