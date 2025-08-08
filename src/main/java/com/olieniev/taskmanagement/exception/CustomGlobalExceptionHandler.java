package com.olieniev.taskmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomGlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundExceptions(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(),
            HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleRegistrationExceptions(RegistrationException ex) {
        return new ResponseEntity<>(ex.getMessage(),
            HttpStatus.CONFLICT);
    }

    @ExceptionHandler(StrategyNotFoundException.class)
    public ResponseEntity<String> handleStrategyNotFoundExceptions(StrategyNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(),
            HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleUpdateUnauthorised.class)
    public ResponseEntity<String> handleRoleUpdateUnauthorisedExceptions(
            RoleUpdateUnauthorised ex
    ) {
        return new ResponseEntity<>(ex.getMessage(),
            HttpStatus.UNAUTHORIZED);
    }
}
