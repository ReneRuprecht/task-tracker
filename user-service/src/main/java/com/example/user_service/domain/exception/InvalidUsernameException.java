package com.example.user_service.domain.exception;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException() {
        super("Invalid Username");
    }
}
