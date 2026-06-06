package com.example.task_service.task.unit.infrastructure.database;

import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskID;
import com.example.task_service.task.domain.TaskTitle;
import com.example.task_service.task.domain.TaskStatus;
import com.example.task_service.task.infrastructure.database.TaskEntity;
import com.example.task_service.task.infrastructure.database.TaskMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    @Test
    void shouldMapDomainTaskToTaskEntityWithStatusOPEN() {
        TaskID id = TaskID.newTaskID();
        TaskTitle title = TaskTitle.newTaskTitle("refactor");
        TaskStatus status = TaskStatus.newTaskStatus();
        Task task = new Task(id, title, status);

        TaskEntity entity = TaskMapper.fromDomain(task);

        assertEquals(id.toString(), entity.getId().toString());
        assertEquals("refactor", entity.getTitle());
        assertEquals("OPEN", entity.getStatus().toString());

    }

    @Test
    void shouldMapDomainTaskToTaskEntityWithStatusClosed() {
        TaskID id = TaskID.newTaskID();
        TaskTitle title = TaskTitle.newTaskTitle("refactor");
        TaskStatus status = TaskStatus.newTaskStatus();
        status.close();
        Task task = new Task(id, title, status);

        TaskEntity entity = TaskMapper.fromDomain(task);

        assertEquals(id.toString(), entity.getId().toString());
        assertEquals("refactor", entity.getTitle());
        assertEquals("CLOSED", entity.getStatus().toString());

    }


}
