package com.olieniev.taskmanagement.service.strategy;

import com.olieniev.taskmanagement.dto.project.UpdateProjectRequestDto;
import com.olieniev.taskmanagement.model.Project;
import com.olieniev.taskmanagement.model.RoleName;
import com.olieniev.taskmanagement.model.User;

public interface ProjectUpdateStrategy {
    RoleName getSupportedRoleName();

    Project updateProjectStatus(User user, Project project, UpdateProjectRequestDto requestDto);
}
