package com.olieniev.taskmanagement.controller;

import com.olieniev.taskmanagement.dto.project.CreateProjectRequestDto;
import com.olieniev.taskmanagement.dto.project.ProjectDto;
import com.olieniev.taskmanagement.dto.project.UpdateProjectRequestDto;
import com.olieniev.taskmanagement.model.User;
import com.olieniev.taskmanagement.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
@Tag(name = "Project Controller",
        description = "All methods of Project Controller")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    @Operation(summary = "Create project method",
            description = "Creates new project from provided create DTO")
    public ProjectDto create(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid CreateProjectRequestDto requestDto) {
        return projectService.create(user, requestDto);
    }

    @GetMapping
    @Operation(summary = "Get user's projects method",
            description = "Retrieves all projects created by logged in user")
    public Page<ProjectDto> getUserProjects(@AuthenticationPrincipal User user, Pageable pageable) {
        return projectService.getUserProjects(user, pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get project by id",
            description = "Retrieves project by provided id")
    public ProjectDto getUserProjects(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update project by id",
            description = "Updates project by provided id and update request DTO")
    public ProjectDto updateProject(
            @PathVariable Long id,
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UpdateProjectRequestDto requestDto
    ) {
        return projectService.updateProject(id, user, requestDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete project by id",
            description = "Deletes project by provided id")
    public void deleteProject(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        projectService.deleteProject(id, user);
    }
}
