package com.olieniev.taskmanagement.repository;

import com.olieniev.taskmanagement.model.Role;
import com.olieniev.taskmanagement.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleName roleName);
}
