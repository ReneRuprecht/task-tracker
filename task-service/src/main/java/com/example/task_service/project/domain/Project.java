package com.example.task_service.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Project {

    @Getter
    private final ProjectID id;

    @Getter
    @Setter
    private ProjectName name;

    public static Project create(String name) {
        return new Project(ProjectID.newProjectID(), ProjectName.newProjectName(name));
    }

}
