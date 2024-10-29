package com.deezzex.crypto.exception;

public class DataNotFoundException extends GeneralApplicationException {

    private static final String MESSAGE = "%s not found by id: %d";

    public DataNotFoundException(String data, Integer id) {
        super(String.format(MESSAGE, data, id));
    }
}
