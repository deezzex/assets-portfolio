package com.deezzex.fiat.model;

import com.deezzex.shared.exception.GeneralApplicationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Currency {
    UAH("UAH"), USD("USD");

    private static final Currency[] VALUES = values();
    private final String name;

    public static Currency getCurrency(String currencyString) {
        return Arrays.stream(VALUES)
                .filter(currency -> currency.getName().equals(currencyString))
                .findAny()
                .orElseThrow(() -> new GeneralApplicationException("Unknown currency: " + currencyString));
    }
}
