package com.olieniev.taskmanagement.service.strategy;

import com.olieniev.taskmanagement.exception.RoleUpdateUnauthorised;
import com.olieniev.taskmanagement.model.Role;
import com.olieniev.taskmanagement.model.RoleName;
import com.olieniev.taskmanagement.model.User;
import com.olieniev.taskmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminRoleUpdateStrategy implements RoleUpdateStrategy {
    private final UserRepository userRepository;

    @Override
    public RoleName getSupportedRoleName() {
        return RoleName.ROLE_ADMIN;
    }

    @Override
    public User updateRole(User user, Role role, User userToUpdate) {
        if (user.getId().equals(userToUpdate.getId())) {
            throw new RoleUpdateUnauthorised("Admin cannot change it's role!");
        }
        if (role.getName().equals(RoleName.ROLE_ADMIN)) {
            throw new RoleUpdateUnauthorised("No new admins required!");
        }
        userToUpdate.setRole(role);
        return userRepository.save(userToUpdate);
    }
}
