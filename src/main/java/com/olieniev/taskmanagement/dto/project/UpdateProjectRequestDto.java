package com.olieniev.taskmanagement.dto.project;

import com.olieniev.taskmanagement.model.Project;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record UpdateProjectRequestDto(
        @Size(min = 8, max = 64)
        String name,
        @Size(min = 8, max = 64)
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Project.Status status
) {
}
