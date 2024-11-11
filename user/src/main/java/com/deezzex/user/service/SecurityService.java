package com.deezzex.user.service;

import com.deezzex.shared.dto.AuthorisationRequest;
import com.deezzex.shared.exception.DataNotFoundException;
import com.deezzex.shared.exception.GeneralApplicationException;
import com.deezzex.user.dto.AuthenticationRequest;
import com.deezzex.user.entity.Session;
import com.deezzex.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;
    private final SessionService sessionService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Session authenticate(AuthenticationRequest authenticationRequest) {
        User user = userService.getUserByEmail(authenticationRequest.getEmail());

        if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPasswordHash())) {
            throw new GeneralApplicationException("Password is incorrect.");
        }

        return sessionService.createSession(user);
    }

    @Transactional(noRollbackFor = DataNotFoundException.class)
    public boolean authorise(AuthorisationRequest authorisationRequest) {
        log.info("Authorisation for token: {}", authorisationRequest.getSessionToken());

        Optional<Session> sessionOptional = sessionService.getSession(authorisationRequest.getSessionToken());
        if (sessionOptional.isPresent()) {
            Session session = sessionOptional.get();
            return session.getStatus().equals("A")
                    && session.getCreatedAt().plusSeconds(360).isAfter(Instant.now());
        } else {
            return false;
        }
    }
}
