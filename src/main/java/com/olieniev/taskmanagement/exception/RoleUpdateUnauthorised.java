package com.olieniev.taskmanagement.exception;

public class RoleUpdateUnauthorised extends RuntimeException {
    public RoleUpdateUnauthorised(String message) {
        super(message);
    }
}
