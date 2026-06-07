package com.example.task_service.project.application.exception;

import com.example.task_service.project.domain.ProjectID;

public class ProjectNotFoundException extends RuntimeException {
    private final ProjectID id;

    public ProjectNotFoundException(ProjectID id) {
        super(String.format("Project with id: %s does not exist", id));
        this.id = id;
    }

    public ProjectID getProjectID() {
        return this.id;
    }
}
