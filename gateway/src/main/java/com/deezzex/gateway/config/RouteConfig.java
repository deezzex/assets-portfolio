package com.deezzex.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Value("${gateway.route.fiat.url}")
    private String fiatServiceRoute;

    @Value("${gateway.route.crypto.url}")
    private String cryptoServiceRoute;

    @Value("${gateway.route.aggregator.url}")
    private String aggregatorServiceRoute;

    @Value("${gateway.route.user.url}")
    private String userServiceRoute;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("fiat_route", r -> r.path("/fiat/**")
                        .uri(fiatServiceRoute))
                .route("crypto_route", r -> r.path("/crypto/**")
                        .uri(cryptoServiceRoute))
                .route("aggregator_route", r -> r.path("/aggregator/**")
                        .uri(aggregatorServiceRoute))
                .route("user_route", r -> r.path("/user/**")
                        .uri(userServiceRoute))
                .build();
    }
}
