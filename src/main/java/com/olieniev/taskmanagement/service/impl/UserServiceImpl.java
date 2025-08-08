package com.olieniev.taskmanagement.service.impl;

import com.olieniev.taskmanagement.dto.user.UpdateRoleRequestDto;
import com.olieniev.taskmanagement.dto.user.UserRegistrationRequestDto;
import com.olieniev.taskmanagement.dto.user.UserResponseDto;
import com.olieniev.taskmanagement.exception.EntityNotFoundException;
import com.olieniev.taskmanagement.exception.RegistrationException;
import com.olieniev.taskmanagement.exception.StrategyNotFoundException;
import com.olieniev.taskmanagement.mapper.UserMapper;
import com.olieniev.taskmanagement.model.Role;
import com.olieniev.taskmanagement.model.RoleName;
import com.olieniev.taskmanagement.model.User;
import com.olieniev.taskmanagement.repository.RoleRepository;
import com.olieniev.taskmanagement.repository.UserRepository;
import com.olieniev.taskmanagement.service.UserService;
import com.olieniev.taskmanagement.service.strategy.RoleUpdateStrategy;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final Map<RoleName, RoleUpdateStrategy> strategyMap;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder,
            List<RoleUpdateStrategy> strategies
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.strategyMap = strategies.stream()
            .collect(Collectors.toMap(
                    RoleUpdateStrategy::getSupportedRoleName, Function.identity())
            );
    }

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
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toResponseDto(
            userRepository.save(user)
        );
    }

    @Override
    public UserResponseDto updateRole(User user, UpdateRoleRequestDto requestDto, Long id) {
        User userToUpdate = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find user by id: " + id)
        );
        Role role = roleRepository.findByName(requestDto.role());
        RoleUpdateStrategy strategy = strategyMap.get(user.getRole().getName());
        if (strategy == null) {
            throw new StrategyNotFoundException("Strategy not found!");
        }
        return userMapper.toResponseDto(strategy.updateRole(user, role, userToUpdate));
    }
}
