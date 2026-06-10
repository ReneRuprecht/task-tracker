package com.example.task_service.task.unit.domain;

import com.example.task_service.task.domain.Task;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskTest {

    @Test
    void shouldCreateTask() {
        UUID projectID = UUID.randomUUID();
        Task task = Task.create("add feature", projectID);

        assertEquals("add feature", task.getTitle().toString());
        assertNotNull(task.getId());
    }

}
