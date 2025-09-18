package com.olieniev.taskmanagement.service.util;

import com.olieniev.taskmanagement.dto.user.UserRegistrationRequestDto;
import com.olieniev.taskmanagement.dto.user.UserResponseDto;
import com.olieniev.taskmanagement.model.Role;
import com.olieniev.taskmanagement.model.RoleName;
import com.olieniev.taskmanagement.model.User;

public class UserUtil {
    public static UserRegistrationRequestDto createRequestDto() {
        return new UserRegistrationRequestDto(
                "test username",
                "12345678",
                "test@email.com",
                "test first",
                "test last"
        );
    }

    public static User createUserFromRequest() {
        User user = new User();
        user.setUsername("test username");
        user.setPassword("12345678");
        user.setEmail("test@email.com");
        user.setFirstName("test first");
        user.setLastName("test last");
        return user;
    }

    public static Role createRole() {
        Role role = new Role();
        role.setId(1L);
        role.setName(RoleName.ROLE_USER);
        return role;
    }

    public static User createUserFromDb() {
        User user = new User();
        user.setId(1L);
        user.setRole(new Role());
        user.setUsername("test username");
        user.setPassword("12345678");
        user.setEmail("test@email.com");
        user.setFirstName("test first");
        user.setLastName("test last");
        return user;
    }

    public static UserResponseDto createUserResponseDto() {
        return new UserResponseDto(
                1L,
                "test username",
                "test@email.com",
                "test first",
                "test last",
                RoleName.ROLE_USER
        );
    }
}
