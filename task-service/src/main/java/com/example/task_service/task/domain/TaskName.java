package com.example.task_service.task.domain;

import com.example.task_service.task.domain.exception.EmptyTaskNameException;

public class TaskName {

    private final String name;

    private TaskName(String name) {
        this.name = name;
    }

    public static TaskName newTaskName(String name) throws EmptyTaskNameException {
        if (name.isBlank()) {
            throw new EmptyTaskNameException();
        }
        return new TaskName(name);
    }

    public String toString() {
        return this.name;
    }
}
