package com.olieniev.taskmanagement.dto.user;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName
) {
}
