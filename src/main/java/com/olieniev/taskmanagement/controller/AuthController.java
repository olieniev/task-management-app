package com.olieniev.taskmanagement.controller;

import com.olieniev.taskmanagement.dto.user.UserLoginRequestDto;
import com.olieniev.taskmanagement.dto.user.UserLoginResponseDto;
import com.olieniev.taskmanagement.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Authentication controller",
        description = "Registration + login methods")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "Login method",
            description = "Logs user in and returns JWT token")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
