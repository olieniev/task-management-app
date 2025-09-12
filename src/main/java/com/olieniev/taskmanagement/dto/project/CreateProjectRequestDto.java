package com.olieniev.taskmanagement.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record CreateProjectRequestDto(
        @NotBlank
        @Size(min = 8, max = 64)
        String name,
        @Size(min = 8, max = 64)
        @NotBlank
        String description,
        @NotNull
        LocalDate startDate,
        @NotNull
        LocalDate endDate
) {
}
