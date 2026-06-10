package com.example.task_service.task.unit.domain;

import com.example.task_service.task.domain.TaskID;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TaskIDTest {

    @Test
    void shouldCreateTaskID() {

        TaskID id = TaskID.newTaskID();

        assertNotEquals("", id.toString());
    }

    @Test
    void shouldCreateTaskIDFromUUID() {

        UUID id = UUID.randomUUID();
        TaskID taskID = TaskID.of(id);

        assertNotNull(taskID.id());
        assertEquals(id.toString(), taskID.toString());
    }

}
