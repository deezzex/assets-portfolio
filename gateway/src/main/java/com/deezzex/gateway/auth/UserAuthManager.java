package com.deezzex.gateway.auth;

import com.deezzex.shared.dto.AuthorisationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAuthManager {

    private final UserAuthClient authClient;

    public Mono<AuthorizationDecision> authorise(Mono<Authentication> authentication,
                                                 AuthorizationContext context) {

        String token = context.getExchange().getRequest().getHeaders().getFirst("token");

        log.info("Authorisation for token: {}", token);

        return authClient
                .authorise(new AuthorisationRequest(token))
                .flatMap(authorisationResponse -> authorisationResponse.isAuthorised()
                        ? Mono.just(new AuthorizationDecision(true))
                        : Mono.just(new AuthorizationDecision(false)));
    }
}
