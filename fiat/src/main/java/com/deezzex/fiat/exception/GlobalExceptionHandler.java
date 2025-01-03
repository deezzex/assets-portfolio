package com.deezzex.fiat.exception;

import com.deezzex.shared.exception.DataNotFoundException;
import com.deezzex.shared.exception.GeneralApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handleException(DataNotFoundException exception) {
        log.error(exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(GeneralApplicationException.class)
    public ResponseEntity<?> handleException(GeneralApplicationException exception) {
        log.error(exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleException(Throwable exception) {
        log.error(exception.getMessage(), exception);

        return ResponseEntity.internalServerError()
                .build();
    }
}
