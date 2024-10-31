package com.deezzex.user.dto;

import lombok.Value;

@Value
public class GetUserDto {
    Integer id;
    String firstName;
    String lastName;
    String email;
    String createdAt;
}
