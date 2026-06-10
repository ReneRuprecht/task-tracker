package com.example.task_service.task.application.commands;

import java.util.Optional;
import java.util.UUID;

public record PatchTaskCommand(UUID id, Optional<String> title, Optional<String> status) {
}
