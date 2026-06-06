package com.example.task_service.task.infrastructure.database;

import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskID;
import com.example.task_service.task.domain.TaskTitle;
import com.example.task_service.task.domain.TaskStatus;

import java.util.UUID;

public class TaskMapper {

    public static TaskEntity fromDomain(Task task) {
        UUID id = UUID.fromString(task.getId().toString());
        TaskEntity.Status status = fromDomainStatus(task);

        return new TaskEntity(id, task.getTitle().toString(), status);
    }

    public static Task fromEntity(TaskEntity entity) {

        TaskID id = TaskID.fromString(entity.getId().toString());
        TaskTitle title = TaskTitle.newTaskTitle(entity.getTitle());
        TaskStatus status = TaskStatus.fromStatus(fromEntityStatus(entity));

        return new Task(id, title, status);
    }

    private static TaskEntity.Status fromDomainStatus(Task task) {
        return switch (task.getStatus().value()) {
            case OPEN -> TaskEntity.Status.OPEN;
            case CLOSED -> TaskEntity.Status.CLOSED;
        };
    }

    private static TaskStatus.Status fromEntityStatus(TaskEntity entity) {
        return switch (entity.getStatus()) {
            case OPEN -> TaskStatus.Status.OPEN;
            case CLOSED -> TaskStatus.Status.CLOSED;
        };
    }
}
