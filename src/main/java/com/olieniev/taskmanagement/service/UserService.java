package com.olieniev.taskmanagement.service;

import com.olieniev.taskmanagement.dto.user.UpdateRoleRequestDto;
import com.olieniev.taskmanagement.dto.user.UpdateUserRequestDto;
import com.olieniev.taskmanagement.dto.user.UserRegistrationRequestDto;
import com.olieniev.taskmanagement.dto.user.UserResponseDto;
import com.olieniev.taskmanagement.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    UserResponseDto updateRole(User user, UpdateRoleRequestDto requestDto, Long id);

    UserResponseDto getInfo(User user);

    UserResponseDto updateUser(User user, UpdateUserRequestDto requestDto);
}
