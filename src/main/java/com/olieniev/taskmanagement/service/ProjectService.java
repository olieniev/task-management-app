package com.olieniev.taskmanagement.service;

import com.olieniev.taskmanagement.dto.project.CreateProjectRequestDto;
import com.olieniev.taskmanagement.dto.project.ProjectDto;
import com.olieniev.taskmanagement.dto.project.UpdateProjectRequestDto;
import com.olieniev.taskmanagement.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {
    ProjectDto create(User user, CreateProjectRequestDto requestDto);

    Page<ProjectDto> getUserProjects(User user, Pageable pageable);

    ProjectDto getProjectById(Long id);

    ProjectDto updateProject(Long id, User user, UpdateProjectRequestDto requestDto);

    void deleteProject(Long id, User user);
}
