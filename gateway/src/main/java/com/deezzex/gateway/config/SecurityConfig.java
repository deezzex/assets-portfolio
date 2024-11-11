package com.deezzex.gateway.config;

import com.deezzex.gateway.auth.UserAuthManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserAuthManager authorisationManager;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/**"))
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/user/security/authenticate").permitAll()
                        .pathMatchers("/user/users").permitAll()
                        .pathMatchers("/**")
                        .access(authorisationManager::authorise)
                        .anyExchange().authenticated());

        return httpSecurity.build();
    }
}
