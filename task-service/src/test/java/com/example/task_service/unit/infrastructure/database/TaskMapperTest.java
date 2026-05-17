package com.example.task_service.unit.infrastructure.database;

import com.example.task_service.domain.Task;
import com.example.task_service.domain.TaskID;
import com.example.task_service.domain.TaskName;
import com.example.task_service.domain.TaskStatus;
import com.example.task_service.infrastructure.database.TaskEntity;
import com.example.task_service.infrastructure.database.TaskMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    @Test
    void shouldMapDomainTaskToTaskEntityWithStatusOPEN() {
        TaskID id = TaskID.newTaskID();
        TaskName name = TaskName.newTaskName("refactor");
        TaskStatus status = TaskStatus.newTaskStatus();
        Task task = new Task(id, name, status);

        TaskEntity entity = TaskMapper.fromDomain(task);

        assertEquals(id.toString(), entity.getId().toString());
        assertEquals("refactor", entity.getName());
        assertEquals("OPEN", entity.getStatus().toString());

    }

    @Test
    void shouldMapDomainTaskToTaskEntityWithStatusClosed() {
        TaskID id = TaskID.newTaskID();
        TaskName name = TaskName.newTaskName("refactor");
        TaskStatus status = TaskStatus.newTaskStatus();
        status.close();
        Task task = new Task(id, name, status);

        TaskEntity entity = TaskMapper.fromDomain(task);

        assertEquals(id.toString(), entity.getId().toString());
        assertEquals("refactor", entity.getName());
        assertEquals("CLOSED", entity.getStatus().toString());

    }


}
