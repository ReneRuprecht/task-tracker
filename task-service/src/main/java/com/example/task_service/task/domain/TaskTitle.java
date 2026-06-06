package com.example.task_service.task.domain;

import com.example.task_service.task.domain.exception.EmptyTaskTitleException;

public class TaskTitle {

    private final String title;

    private TaskTitle(String title) {
        this.title = title;
    }

    public static TaskTitle newTaskTitle(String title) throws EmptyTaskTitleException {
        if (title.isBlank()) {
            throw new EmptyTaskTitleException();
        }
        return new TaskTitle(title);
    }

    public String toString() {
        return this.title;
    }
}
