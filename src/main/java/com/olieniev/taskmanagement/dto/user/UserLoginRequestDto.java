package com.olieniev.taskmanagement.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(
        @NotBlank
        @Size(min = 8, max = 64)
        String username,
        @NotBlank
        @Size(min = 8, max = 64)
        String password
) {
}
