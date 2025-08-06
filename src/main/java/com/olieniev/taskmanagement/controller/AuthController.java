package com.olieniev.taskmanagement.controller;

import com.olieniev.taskmanagement.dto.user.UserLoginRequestDto;
import com.olieniev.taskmanagement.dto.user.UserLoginResponseDto;
import com.olieniev.taskmanagement.dto.user.UserRegistrationRequestDto;
import com.olieniev.taskmanagement.dto.user.UserResponseDto;
import com.olieniev.taskmanagement.security.AuthenticationService;
import com.olieniev.taskmanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication controller",
        description = "Registration + login methods")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "Login method",
            description = "Logs user in and returns JWT token")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/registration")
    @Operation(summary = "Registration method",
            description = "Registers user")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return userService.register(requestDto);
    }
}
