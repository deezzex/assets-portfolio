package com.deezzex.user.ingress.controller;

import com.deezzex.user.dto.CreateUserDto;
import com.deezzex.user.dto.GetUserDto;
import com.deezzex.user.entity.User;
import com.deezzex.user.mapper.UserMapper;
import com.deezzex.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<GetUserDto> createUser(@RequestBody CreateUserDto createUserDto) {
        User user = userService.createUser(createUserDto);
        GetUserDto getUserDto = userMapper.toGetUserDto(user);

        return ResponseEntity.ok(getUserDto);
    }
}
