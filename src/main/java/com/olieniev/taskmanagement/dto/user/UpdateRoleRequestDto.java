package com.olieniev.taskmanagement.dto.user;

import com.olieniev.taskmanagement.model.RoleName;
import jakarta.validation.constraints.NotNull;

public record UpdateRoleRequestDto(
        @NotNull
        RoleName role
) {}
