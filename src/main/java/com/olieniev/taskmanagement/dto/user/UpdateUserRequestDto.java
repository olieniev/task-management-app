package com.olieniev.taskmanagement.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequestDto(
        @Size(min = 8, max = 64)
        String password,
        @Email
        String email,
        String firstName,
        String lastName
) {
}
