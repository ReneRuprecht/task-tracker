package com.example.task_service.domain.exception;

public class InvalidTaskIDStringException extends RuntimeException {
    public  InvalidTaskIDStringException(){
        super("Invalid UUID");
    }
}
