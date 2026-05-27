package com.example.user_service.domain.exception;

public class UserEmailAlreadyExistsException extends RuntimeException {
    public UserEmailAlreadyExistsException() {
        super("Email already exists");
    }
}
