package com.example.task_service.task.infrastructure.http.request;

import java.util.Optional;

public record PatchTaskRequest(Optional<String> title, Optional<String> status) {
}
