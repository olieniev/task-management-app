package com.olieniev.taskmanagement.service.strategy;

import com.olieniev.taskmanagement.dto.project.UpdateProjectRequestDto;
import com.olieniev.taskmanagement.exception.ProjectUpdateUnauthorised;
import com.olieniev.taskmanagement.mapper.ProjectMapper;
import com.olieniev.taskmanagement.model.Project;
import com.olieniev.taskmanagement.model.RoleName;
import com.olieniev.taskmanagement.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminProjectUpdateStrategy implements ProjectUpdateStrategy {
    private final ProjectMapper projectMapper;

    @Override
    public RoleName getSupportedRoleName() {
        return RoleName.ROLE_ADMIN;
    }

    @Override
    public Project updateProjectStatus(
            User user, Project project, UpdateProjectRequestDto requestDto
    ) {
        if (requestDto.status().equals(project.getStatus())) {
            throw new ProjectUpdateUnauthorised(
                    "The desired status is already set for the given project!"
            );
        }
        projectMapper.updateModelFromDto(project, requestDto);
        return project;
    }
}
