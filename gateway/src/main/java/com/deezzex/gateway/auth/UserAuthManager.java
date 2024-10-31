package com.deezzex.gateway.auth;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserAuthManager {

    public Mono<AuthorizationDecision> authorise(Mono<Authentication> authentication,
                                                 AuthorizationContext context) {
        return Mono.just(new AuthorizationDecision(true));
    }
}
