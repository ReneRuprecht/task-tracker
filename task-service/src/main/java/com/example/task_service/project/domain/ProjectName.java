package com.example.task_service.project.domain;

import com.example.task_service.project.domain.exception.EmptyProjectNameException;

public class ProjectName {

    private final String name;

    private ProjectName(String title) {
        this.name = title;
    }

    public static ProjectName newProjectName(String title) {
        if (title.isBlank()) throw new EmptyProjectNameException();

        return new ProjectName(title);
    }

    public String name() {
        return this.name;
    }
}
