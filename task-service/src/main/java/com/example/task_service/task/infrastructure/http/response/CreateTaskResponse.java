package com.example.task_service.task.infrastructure.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record CreateTaskResponse(String id,
                                 String title,
                                 String status,
                                 @JsonProperty("project_id") UUID projectID) {
}
