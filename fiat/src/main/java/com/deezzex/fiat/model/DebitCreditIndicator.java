package com.deezzex.fiat.model;

import com.deezzex.shared.exception.GeneralApplicationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum DebitCreditIndicator {
    D('D'), C('C');

    private static final DebitCreditIndicator[] VALUES = values();
    private final Character value;

    public static DebitCreditIndicator getIndicator(char indicatorString) {
        return Arrays.stream(VALUES)
                .filter(currency -> currency.getValue().equals(indicatorString))
                .findAny()
                .orElseThrow(() -> new GeneralApplicationException("Unknown debit credit indicator: " + indicatorString));
    }
}
