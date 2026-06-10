package com.example.task_service.task.domain;

import java.util.UUID;

public class TaskProjectID {

    private final UUID id;

    private TaskProjectID(UUID id) {
        this.id = id;
    }

    public static TaskProjectID of(UUID id) {
        return new TaskProjectID(id);
    }

    public UUID id() {
        return this.id;
    }

    public String toString() {
        return this.id().toString();
    }

}
