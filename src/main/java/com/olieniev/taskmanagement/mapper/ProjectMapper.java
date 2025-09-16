package com.olieniev.taskmanagement.mapper;

import com.olieniev.taskmanagement.config.MapperConfig;
import com.olieniev.taskmanagement.dto.project.CreateProjectRequestDto;
import com.olieniev.taskmanagement.dto.project.ProjectDto;
import com.olieniev.taskmanagement.dto.project.UpdateProjectRequestDto;
import com.olieniev.taskmanagement.model.Project;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class)
public interface ProjectMapper {
    Project toModel(CreateProjectRequestDto requestDto);

    @Mapping(target = "ownerId", source = "owner.id")
    ProjectDto toResponseDto(Project project);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto(@MappingTarget Project project, UpdateProjectRequestDto requestDto);
}
