package com.example.task_service.project.domain;

import java.util.UUID;

public class ProjectID {

    private final UUID id;

    private ProjectID(UUID id) {
        this.id = id;

    }

    public static ProjectID newProjectID() {
        UUID id = UUID.randomUUID();
        return new ProjectID(id);
    }

    public static ProjectID of(UUID id) {
        return new ProjectID(id);
    }

    public UUID id() {
        return this.id;
    }

    public String toString() {
        return this.id.toString();
    }
}
