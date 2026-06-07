package com.example.task_service.project.infrastructure.http.list;

import com.example.task_service.project.infrastructure.http.ProjectResponse;

import java.util.List;

public record ListProjectsResponse(List<ProjectResponse> projects) {
}
