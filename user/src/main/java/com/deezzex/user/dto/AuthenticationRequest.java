package com.deezzex.user.dto;

import lombok.Value;

@Value
public class AuthenticationRequest {
    String email;
    String password;
}
