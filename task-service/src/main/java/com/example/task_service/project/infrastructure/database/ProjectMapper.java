package com.example.task_service.project.infrastructure.database;

import com.example.task_service.project.domain.Project;
import com.example.task_service.project.domain.ProjectID;
import com.example.task_service.project.domain.ProjectName;

public class ProjectMapper {

    public static ProjectEntity toEntity(Project project) {
        return new ProjectEntity(
                project.getId().id(),
                project.getName().name()
        );
    }

    public static Project toDomain(ProjectEntity entity) {
        return new Project(
                ProjectID.of(entity.getId()),
                ProjectName.newProjectName(entity.getName())
        );
    }
}
