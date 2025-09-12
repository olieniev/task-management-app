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
public class UserProjectUpdateStrategy implements ProjectUpdateStrategy {
    private final ProjectMapper projectMapper;

    @Override
    public RoleName getSupportedRoleName() {
        return RoleName.ROLE_USER;
    }

    @Override
    public Project updateProjectStatus(
            User user, Project project, UpdateProjectRequestDto requestDto
    ) {
        if (!user.getId().equals(project.getOwner().getId())) {
            throw new ProjectUpdateUnauthorised("Unauthorised to update project status!");
        }
        projectMapper.updateModelFromDto(project, requestDto);
        return project;
    }
}
