package com.example.user_service.domain.exception;

public class InvalidUserEmailException extends RuntimeException {
    public InvalidUserEmailException(String msg) {
        super(msg);
    }
}
