package com.example.task_service.task.domain.exception;

public class TaskNotFoundException extends  RuntimeException{

    public TaskNotFoundException(){
        super("Task not found");
    }
}
