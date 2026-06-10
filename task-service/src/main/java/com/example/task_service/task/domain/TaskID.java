package com.example.task_service.task.domain;

import java.util.UUID;

public class TaskID {

    private final UUID id;

    private TaskID(UUID id) {
        this.id = id;

    }

    public static TaskID newTaskID() {
        UUID id = UUID.randomUUID();
        return new TaskID(id);
    }

    public static TaskID of(UUID id) {

        return new TaskID(id);
    }

    public UUID id() {
        return this.id;
    }

    public String toString() {
        return this.id.toString();
    }
}
