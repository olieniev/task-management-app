package com.olieniev.taskmanagement.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequestDto(
        @NotBlank
        @Size(min = 8, max = 64)
        String username,
        @NotBlank
        @Size(min = 8, max = 64)
        String password,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName
) {
}
