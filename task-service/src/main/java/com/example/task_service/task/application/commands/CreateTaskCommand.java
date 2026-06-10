package com.example.task_service.task.application.commands;

import java.util.UUID;

public record CreateTaskCommand(String title, UUID projectID) {
}
