package com.olieniev.taskmanagement.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RoleName {
    ROLE_ADMIN,
    ROLE_USER;

    @JsonCreator
    public static RoleName fromString(String value) {
        return RoleName.valueOf("ROLE_" + value.toUpperCase());
    }
}
