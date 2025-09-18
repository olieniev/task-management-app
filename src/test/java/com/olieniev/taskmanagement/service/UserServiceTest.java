package com.olieniev.taskmanagement.service;

import static com.olieniev.taskmanagement.service.util.UserUtil.createRequestDto;
import static com.olieniev.taskmanagement.service.util.UserUtil.createRole;
import static com.olieniev.taskmanagement.service.util.UserUtil.createUserFromDb;
import static com.olieniev.taskmanagement.service.util.UserUtil.createUserFromRequest;
import static com.olieniev.taskmanagement.service.util.UserUtil.createUserResponseDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.olieniev.taskmanagement.dto.user.UserRegistrationRequestDto;
import com.olieniev.taskmanagement.dto.user.UserResponseDto;
import com.olieniev.taskmanagement.mapper.UserMapper;
import com.olieniev.taskmanagement.model.Role;
import com.olieniev.taskmanagement.model.RoleName;
import com.olieniev.taskmanagement.model.User;
import com.olieniev.taskmanagement.repository.RoleRepository;
import com.olieniev.taskmanagement.repository.UserRepository;
import com.olieniev.taskmanagement.service.impl.UserServiceImpl;
import com.olieniev.taskmanagement.service.strategy.RoleUpdateStrategy;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private List<RoleUpdateStrategy> strategies;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("""
        Registering new user returns user dto    
            """)
    void registerUser_validData_success() {
        UserRegistrationRequestDto requestDto = createRequestDto();
        User user = createUserFromRequest();
        Role role = createRole();
        User dbUser = createUserFromDb();
        UserResponseDto expected = createUserResponseDto();

        when(userRepository.existsByEmail(requestDto.email())).thenReturn(false);
        when(userRepository.existsByUsername(requestDto.username())).thenReturn(false);
        when(userMapper.toModel(requestDto)).thenReturn(user);
        when(roleRepository.findByName(RoleName.ROLE_USER)).thenReturn(role);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("12345678");
        when(userRepository.save(user)).thenReturn(dbUser);
        when(userMapper.toResponseDto(dbUser)).thenReturn(expected);

        UserResponseDto actual = userService.register(requestDto);

        assertEquals(expected, actual);
        verify(userRepository, times(1)).existsByEmail(requestDto.email());
        verify(userRepository, times(1)).existsByUsername(requestDto.username());
        verify(userMapper, times(1)).toModel(requestDto);
        verify(roleRepository, times(1)).findByName(RoleName.ROLE_USER);
        verify(passwordEncoder, times(1)).encode(user.getPassword());
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).toResponseDto(dbUser);
    }
}
