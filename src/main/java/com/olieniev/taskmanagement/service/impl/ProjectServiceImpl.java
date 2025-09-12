package com.olieniev.taskmanagement.service.impl;

import com.olieniev.taskmanagement.dto.project.CreateProjectRequestDto;
import com.olieniev.taskmanagement.dto.project.ProjectDto;
import com.olieniev.taskmanagement.dto.project.UpdateProjectRequestDto;
import com.olieniev.taskmanagement.exception.EntityNotFoundException;
import com.olieniev.taskmanagement.exception.ProjectUpdateUnauthorised;
import com.olieniev.taskmanagement.exception.StrategyNotFoundException;
import com.olieniev.taskmanagement.mapper.ProjectMapper;
import com.olieniev.taskmanagement.model.Project;
import com.olieniev.taskmanagement.model.RoleName;
import com.olieniev.taskmanagement.model.User;
import com.olieniev.taskmanagement.repository.ProjectRepository;
import com.olieniev.taskmanagement.service.ProjectService;
import com.olieniev.taskmanagement.service.strategy.ProjectUpdateStrategy;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final Map<RoleName, ProjectUpdateStrategy> strategyMap;

    public ProjectServiceImpl(
            ProjectRepository projectRepository,
            ProjectMapper projectMapper,
            List<ProjectUpdateStrategy> strategies
    ) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(
                        ProjectUpdateStrategy::getSupportedRoleName, Function.identity()
                ));
    }

    @Override
    public ProjectDto create(User user, CreateProjectRequestDto requestDto) {
        Project project = projectMapper.toModel(requestDto);
        project.setOwner(user);
        return projectMapper.toResponseDto(projectRepository.save(project));
    }

    @Override
    public Page<ProjectDto> getUserProjects(User user, Pageable pageable) {
        return projectRepository.findByOwnerId(user.getId(), pageable)
                .map(projectMapper::toResponseDto);
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        return projectMapper.toResponseDto(projectRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Project does not exist with id: " + id)
        ));
    }

    @Override
    public ProjectDto updateProject(Long id, User user, UpdateProjectRequestDto requestDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Could not find a project with id: " + id)
                );
        ProjectUpdateStrategy strategy = strategyMap.get(user.getRole().getName());
        if (strategy == null) {
            throw new StrategyNotFoundException("Strategy not found!");
        }
        Project updatedProject = strategy.updateProjectStatus(user, project, requestDto);
        return projectMapper.toResponseDto(projectRepository.save(updatedProject));
    }

    @Override
    public void deleteProject(Long id, User user) {
        if (!user.getRole().getName().equals(RoleName.ROLE_ADMIN)) {
            throw new ProjectUpdateUnauthorised("Only admin is able to delete project!");
        }
        projectRepository.deleteById(id);
    }
}
