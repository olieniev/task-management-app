package com.olieniev.taskmanagement.service.impl;

import com.olieniev.taskmanagement.dto.user.UserRegistrationRequestDto;
import com.olieniev.taskmanagement.dto.user.UserResponseDto;
import com.olieniev.taskmanagement.exception.RegistrationException;
import com.olieniev.taskmanagement.mapper.UserMapper;
import com.olieniev.taskmanagement.model.Role;
import com.olieniev.taskmanagement.model.RoleName;
import com.olieniev.taskmanagement.model.User;
import com.olieniev.taskmanagement.repository.RoleRepository;
import com.olieniev.taskmanagement.repository.UserRepository;
import com.olieniev.taskmanagement.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.email())
                || userRepository.existsByUsername(requestDto.username())) {
            throw new RegistrationException(
                "User already exists with email: " + requestDto.email()
                    + "and username: " + requestDto.username());
        }
        User user = userMapper.toModel(requestDto);
        Role role = roleRepository.findByName(RoleName.ROLE_USER);
        user.setRoles(Set.of(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toResponseDto(
            userRepository.save(user)
        );
    }
}
