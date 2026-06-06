package com.example.task_service.task.domain.exception;

public class EmptyTaskTitleException extends RuntimeException {

    public EmptyTaskTitleException() {
        super("TaskTitle cannot be empty");
    }
}
