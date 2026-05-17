package com.example.task_service.unit.infrastructure.http;

import com.example.task_service.domain.Task;
import com.example.task_service.infrastructure.http.CreateTaskRequest;
import com.example.task_service.infrastructure.http.TaskMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskMapperTest {

    @Test
    void shouldMapCreateTaskRequestToDomainTask() {

        CreateTaskRequest createTaskRequest = new CreateTaskRequest("feature");

        Task task = TaskMapper.fromRequest(createTaskRequest);

        assertFalse(task.getId().toString().isBlank());
        assertEquals("feature", task.getName().toString());
        assertEquals("OPEN", task.getStatus().value().toString());
    }
}
