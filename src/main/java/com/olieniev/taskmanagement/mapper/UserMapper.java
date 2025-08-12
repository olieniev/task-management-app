package com.olieniev.taskmanagement.mapper;

import com.olieniev.taskmanagement.config.MapperConfig;
import com.olieniev.taskmanagement.dto.user.UpdateUserRequestDto;
import com.olieniev.taskmanagement.dto.user.UserRegistrationRequestDto;
import com.olieniev.taskmanagement.dto.user.UserResponseDto;
import com.olieniev.taskmanagement.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    @Mapping(source = "role.name", target = "role")
    UserResponseDto toResponseDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", ignore = true)
    void updateUserFromDto(UpdateUserRequestDto requestDto, @MappingTarget User user);
}
