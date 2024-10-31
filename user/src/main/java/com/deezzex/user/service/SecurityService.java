package com.deezzex.user.service;

import com.deezzex.shared.exception.DataNotFoundException;
import com.deezzex.shared.exception.GeneralApplicationException;
import com.deezzex.user.dto.AuthenticationRequest;
import com.deezzex.user.dto.AuthorisationRequest;
import com.deezzex.user.entity.Session;
import com.deezzex.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;
    private final SessionService sessionService;
    private final PasswordEncoder passwordEncoder;

    public Session authenticate(AuthenticationRequest authenticationRequest) {
        User user = userService.getUserByEmail(authenticationRequest.getEmail());

        if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPasswordHash())) {
            throw new GeneralApplicationException("Password is incorrect.");
        }

        return sessionService.createSession(user);
    }

    public boolean authorise(AuthorisationRequest authorisationRequest) {
        try {
            Session session = sessionService.getSession(authorisationRequest.getSessionToken());
            return session.getStatus().equals("A")
                    && session.getCreatedAt().plusSeconds(360).isAfter(Instant.now());
        } catch (DataNotFoundException e) {
            return false;
        }
    }
}
