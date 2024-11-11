package com.deezzex.user.ingress.controller;

import com.deezzex.user.dto.AuthenticationRequest;
import com.deezzex.user.dto.AuthenticationResponse;
import com.deezzex.shared.dto.AuthorisationRequest;
import com.deezzex.shared.dto.AuthorisationResponse;
import com.deezzex.user.entity.Session;
import com.deezzex.user.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security")
@RequiredArgsConstructor
public class SecurityController {

    private final SecurityService securityService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        Session session = securityService.authenticate(authenticationRequest);

        return ResponseEntity.ok(new AuthenticationResponse(session.getId()));
    }

    @PostMapping("/authorise")
    public AuthorisationResponse authenticate(@RequestBody AuthorisationRequest authorisationRequest) {
        boolean isAuthorised = securityService.authorise(authorisationRequest);

        return new AuthorisationResponse(isAuthorised);
    }
}
