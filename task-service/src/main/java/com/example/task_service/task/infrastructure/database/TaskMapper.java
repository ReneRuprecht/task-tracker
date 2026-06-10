package com.example.task_service.task.infrastructure.database;

import com.example.task_service.task.domain.*;

import java.util.UUID;

public class TaskMapper {

    public static TaskEntity toEntity(Task task) {
        UUID id = UUID.fromString(task.getId().toString());
        TaskEntity.Status status = fromDomainStatus(task);
        UUID projectID = task.getProjectID().id();

        return new TaskEntity(id, task.getTitle().toString(), status, projectID);
    }

    public static Task toDomain(TaskEntity entity) {

        TaskID id = TaskID.of(entity.getId());
        TaskTitle title = TaskTitle.newTaskTitle(entity.getTitle());
        TaskStatus status = TaskStatus.fromStatus(fromEntityStatus(entity));
        TaskProjectID projectID = TaskProjectID.of(entity.getProjectID());

        return new Task(id, title, status, projectID);
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
