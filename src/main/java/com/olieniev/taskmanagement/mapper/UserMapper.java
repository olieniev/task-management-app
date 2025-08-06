package com.olieniev.taskmanagement.mapper;

import com.olieniev.taskmanagement.config.MapperConfig;
import com.olieniev.taskmanagement.dto.user.UserRegistrationRequestDto;
import com.olieniev.taskmanagement.dto.user.UserResponseDto;
import com.olieniev.taskmanagement.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toResponseDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);
}
