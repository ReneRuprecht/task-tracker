package com.example.task_service.infrastructure.http;

import java.util.Optional;

public record PatchTaskRequest(Optional<String> name, Optional<String> status) {
}
