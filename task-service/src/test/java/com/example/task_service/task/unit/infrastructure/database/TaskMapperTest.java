package com.example.task_service.task.unit.infrastructure.database;

import com.example.task_service.task.domain.Task;
import com.example.task_service.task.infrastructure.database.TaskEntity;
import com.example.task_service.task.infrastructure.database.TaskMapper;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    @Test
    void shouldMapDomainTaskToTaskEntityWithStatusOPEN() {
        UUID projectID = UUID.randomUUID();
        Task task = Task.create("refactor", projectID);

        TaskEntity entity = TaskMapper.toEntity(task);

        assertEquals(task.getId().toString(), entity.getId().toString());
        assertEquals("refactor", entity.getTitle());
        assertEquals("OPEN", entity.getStatus().toString());

    }

    @Test
    void shouldMapDomainTaskToTaskEntityWithStatusClosed() {
        UUID projectID = UUID.randomUUID();
        Task task = Task.create("refactor", projectID);
        task.getStatus().close();

        TaskEntity entity = TaskMapper.toEntity(task);

        assertEquals(task.getId().toString(), entity.getId().toString());
        assertEquals("refactor", entity.getTitle());
        assertEquals("CLOSED", entity.getStatus().toString());

    }


}
