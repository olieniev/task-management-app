package com.olieniev.taskmanagement.service.strategy;

import com.olieniev.taskmanagement.model.Role;
import com.olieniev.taskmanagement.model.RoleName;
import com.olieniev.taskmanagement.model.User;

public interface RoleUpdateStrategy {
    RoleName getSupportedRoleName();

    User updateRole(User user, Role role, User userToUpdate);
}
