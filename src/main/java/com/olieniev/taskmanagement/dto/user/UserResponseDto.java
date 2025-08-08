package com.olieniev.taskmanagement.dto.user;

import com.olieniev.taskmanagement.model.RoleName;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        RoleName role
) {
}
