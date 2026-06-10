package com.example.task_service.task.infrastructure.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record CreateTaskRequest(String title, @JsonProperty("project_id") UUID projectID) {
}
