package com.olieniev.taskmanagement.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RoleName {
    ROLE_ADMIN,
    ROLE_USER;

    @JsonCreator
    public static RoleName fromString(String value) {
        if (value.toUpperCase().startsWith("ROLE_")) {
            return RoleName.valueOf(value.toUpperCase());
        }
        return RoleName.valueOf("ROLE_" + value.toUpperCase());
    }
}
