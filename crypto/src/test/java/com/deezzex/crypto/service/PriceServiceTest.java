package com.deezzex.crypto.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PriceServiceTest {

    @Test
    @SneakyThrows
    void test(){
        generatePrices().doOnNext(next -> System.out.println(next)).subscribe();

        Thread.sleep(10000);
    }

    private final List<String> cryptocurrencies = List.of("BTC", "ETH", "XRP", "LTC", "ADA");
    private final Random random = new Random();

    public Flux<Map<String, BigDecimal>> generatePrices() {
        return Flux.interval(Duration.ofSeconds(1)) // Emit every second
                .map(tick -> cryptocurrencies.stream()
                        .collect(Collectors.toMap(
                                crypto -> crypto,
                                crypto -> generateRandomPrice(crypto)
                        ))
                );
    }

    private BigDecimal generateRandomPrice(String crypto) {
        double min = 50.0;
        double max = 50000.0;
        double price = min + (max - min) * random.nextDouble();
        return BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}