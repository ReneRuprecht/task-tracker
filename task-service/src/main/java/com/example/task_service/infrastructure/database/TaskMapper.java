package com.example.task_service.infrastructure.database;

import com.example.task_service.domain.Task;

import java.util.UUID;

public class TaskMapper {

    public static TaskEntity fromDomain(Task task) {
        UUID id = UUID.fromString(task.getId().toString());
        TaskEntity.Status status = fromDomainStatus(task);

        return new TaskEntity(id, task.getName().toString(), status);
    }

    private static TaskEntity.Status fromDomainStatus(Task task) {
        return switch (task.getStatus().value()) {
            case OPEN -> TaskEntity.Status.OPEN;
            case CLOSED -> TaskEntity.Status.CLOSED;
        };
    }
}
