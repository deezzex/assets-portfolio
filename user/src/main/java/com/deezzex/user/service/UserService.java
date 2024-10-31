package com.deezzex.user.service;

import com.deezzex.shared.exception.DataNotFoundException;
import com.deezzex.user.dto.CreateUserDto;
import com.deezzex.user.entity.User;
import com.deezzex.user.repository.UserRepository;
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
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(CreateUserDto createUserDto) {
        log.info("Creating user: {}", createUserDto);

        var user = User.builder()
                .email(createUserDto.getEmail())
                .firstName(createUserDto.getFirstName())
                .lastName(createUserDto.getLastName())
                .passwordHash(passwordEncoder.encode(createUserDto.getPassword()))
                .createdAt(Instant.now())
                .build();

        user = userRepository.save(user);

        log.info("User has been created successfully: {}", user);

        return user;
    }

    public User getUser(Integer userId) {
        log.info("Getting user by id: {}", userId);

        return userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User", userId));
    }

    public User getUserByEmail(String email) {
        log.info("Getting user by email: {}", email);

        return userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("User", -1));
    }
}
