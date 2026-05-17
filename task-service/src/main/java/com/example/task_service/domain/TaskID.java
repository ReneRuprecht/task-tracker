package com.example.task_service.domain;

import com.example.task_service.domain.exception.InvalidTaskIDStringException;

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

    public static TaskID fromString(String id) throws InvalidTaskIDStringException {

        try {
            UUID uuid = UUID.fromString(id);
            return new TaskID(uuid);
        } catch (IllegalArgumentException ex) {
            throw new InvalidTaskIDStringException();
        }
    }

    public String toString() {
        return this.id.toString();
    }
}
