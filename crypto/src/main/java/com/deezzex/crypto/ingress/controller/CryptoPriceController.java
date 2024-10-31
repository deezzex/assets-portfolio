package com.deezzex.crypto.ingress.controller;

import com.deezzex.crypto.model.Token;
import com.deezzex.crypto.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class CryptoPriceController {

    private final PriceService cryptoPriceService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<Token, BigDecimal>> getCryptoPrices() {
        return cryptoPriceService.generatePrices();
    }
}
