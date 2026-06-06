package com.example.task_service.task.domain.exception;

public class EmptyTaskNameException extends RuntimeException {

    public EmptyTaskNameException() {
        super("TaskName cannot be empty");
    }
}
