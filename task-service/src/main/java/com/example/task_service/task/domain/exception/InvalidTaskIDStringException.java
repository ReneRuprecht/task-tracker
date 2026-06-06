package com.example.task_service.task.domain.exception;

public class InvalidTaskIDStringException extends RuntimeException {
    public  InvalidTaskIDStringException(){
        super("Invalid UUID");
    }
}
