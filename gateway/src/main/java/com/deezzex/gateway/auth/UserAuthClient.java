package com.deezzex.gateway.auth;

import com.deezzex.shared.dto.AuthorisationRequest;
import com.deezzex.shared.dto.AuthorisationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserAuthClient {

    private final WebClient webClient;

    public Mono<AuthorisationResponse> authorise(AuthorisationRequest request) {
        return webClient.post()
                .uri("/security/authorise")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AuthorisationResponse.class);
    }
}
