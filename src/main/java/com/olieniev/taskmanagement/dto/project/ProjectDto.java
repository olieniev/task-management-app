package com.olieniev.taskmanagement.dto.project;

import com.olieniev.taskmanagement.model.Project;
import java.time.LocalDate;

public record ProjectDto(
        Long id,
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Long ownerId,
        Project.Status status
) {
}
