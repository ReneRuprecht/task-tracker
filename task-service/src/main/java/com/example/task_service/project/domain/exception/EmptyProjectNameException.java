package com.example.task_service.project.domain.exception;

public class EmptyProjectNameException extends RuntimeException {
    public EmptyProjectNameException() {
        super("ProjectName cannot be empty");
    }
}
