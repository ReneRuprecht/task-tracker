package com.example.task_service.task.unit.domain;

import com.example.task_service.task.domain.TaskID;
import com.example.task_service.task.domain.exception.InvalidTaskIDStringException;
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
    void shouldCreateTaskIDFromString() {

        UUID id = UUID.randomUUID();
        TaskID taskID = TaskID.fromString(id.toString());

        assertNotEquals("", taskID.toString());
        assertEquals(id.toString(), taskID.toString());
    }
    @Test
    void shouldThrowInvalidArgumentExceptionFromInvalidString() {

        Exception exception = assertThrows(
                InvalidTaskIDStringException.class,()->{

            TaskID.fromString("invalid");
        });

        assertEquals("Invalid UUID",exception.getMessage());

    }
}
