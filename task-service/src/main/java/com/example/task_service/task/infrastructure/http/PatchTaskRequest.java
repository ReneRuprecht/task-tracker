package com.example.task_service.task.infrastructure.http;

import java.util.Optional;

public record PatchTaskRequest(Optional<String> name, Optional<String> status) {
}
