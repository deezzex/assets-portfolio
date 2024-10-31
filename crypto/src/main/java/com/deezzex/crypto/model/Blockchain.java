package com.deezzex.crypto.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum Blockchain {
    BITCOIN("Bitcoin"), TONCOIN("Toncoin"), TRON("Tron"), ARBITRUM_ONE("Arbitrum One");

    private static final Blockchain[] VALUES = values();
    private final String name;


    public static Blockchain getFromString(String string) {
        return Arrays.stream(VALUES)
                .filter(blockchain -> blockchain.getName().equals(string))
                .findAny()
                .orElseThrow(() -> new GeneralApplicationException("Unknown blockchain: " + string));
    }
}
