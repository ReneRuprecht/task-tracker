package com.example.task_service.project.infrastructure.http.create;

import java.util.UUID;

public record CreateProjectResponse(UUID id, String name) {
}
