package com.deezzex.user.service;

import com.deezzex.shared.exception.DataNotFoundException;
import com.deezzex.user.entity.Session;
import com.deezzex.user.entity.User;
import com.deezzex.user.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    public Session createSession(User user) {
        log.info("Creating session for user: {}", user.getId());

        var session = Session.builder()
                .createdAt(Instant.now())
                .status("A")
                .id(UUID.randomUUID().toString())
                .user(user)
                .build();

        session = sessionRepository.save(session);

        log.info("Session has been created successfully: {}", session);

        return session;
    }

    public Optional<Session> getSession(String id) {
        return sessionRepository.findById(id);
    }
}
