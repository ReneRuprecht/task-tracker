package com.example.task_service.domain.exception;

public class TaskNotFoundException extends  RuntimeException{

    public TaskNotFoundException(){
        super("Task not found");
    }
}
