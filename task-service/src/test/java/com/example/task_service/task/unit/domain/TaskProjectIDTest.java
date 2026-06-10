package com.example.task_service.task.unit.domain;

import com.example.task_service.task.domain.TaskID;
import com.example.task_service.task.domain.TaskProjectID;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TaskProjectIDTest {

    @Test
    void shouldCreateTaskProjectID() {
        UUID id = UUID.randomUUID();
        TaskProjectID actual = TaskProjectID.of(id);

        assertNotNull(actual.id());
    }

}
