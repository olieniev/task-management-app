package com.olieniev.taskmanagement.exception;

public class ProjectUpdateUnauthorised extends RuntimeException {
    public ProjectUpdateUnauthorised(String message) {
        super(message);
    }
}
