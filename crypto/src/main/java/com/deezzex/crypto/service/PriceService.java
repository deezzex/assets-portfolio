package com.deezzex.crypto.service;

import com.deezzex.crypto.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceService {
    private final List<Token> tokens = Arrays.asList(Token.values());
    private final Random random = new Random();

    public Flux<Map<Token, BigDecimal>> generatePrices() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> tokens.stream()
                        .collect(Collectors.toMap(
                                crypto -> crypto,
                                this::generateRandomPrice
                        ))
                );
    }

    private BigDecimal generateRandomPrice(Token token) {
        double min = 50.0;
        double max = 500.0;
        double price = min + (max - min) * random.nextDouble();
        return BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
