package com.deezzex.user.dto;

import lombok.Value;

@Value
public class CreateUserDto {
    String firstName;
    String lastName;
    String email;
    String password;
}
