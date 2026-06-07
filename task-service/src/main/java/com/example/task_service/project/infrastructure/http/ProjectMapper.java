package com.example.task_service.project.infrastructure.http;

import com.example.task_service.project.domain.Project;
import com.example.task_service.project.infrastructure.http.create.CreateProjectResponse;
import com.example.task_service.project.infrastructure.http.list.ListProjectsResponse;

import java.util.List;

public class ProjectMapper {

    public static CreateProjectResponse toCreateProjectResponse(Project project) {
        return new CreateProjectResponse(project.getId().id(), project.getName().name());
    }

    public static ListProjectsResponse toListProjectsResponse(List<Project> projects) {

        return new ListProjectsResponse(projects
                .stream()
                .map(ProjectMapper::toProjectResponse)
                .toList());
    }

    public static ProjectResponse toProjectResponse(Project project) {
        return new ProjectResponse(project.getId().id(), project.getName().name());
    }
}
