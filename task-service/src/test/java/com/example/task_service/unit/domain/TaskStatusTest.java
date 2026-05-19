package com.example.task_service.unit.domain;

import com.example.task_service.domain.TaskStatus;
import com.example.task_service.domain.exception.InvalidTaskStatusException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskStatusTest {

    @Test
    void shouldCreateOpenTaskStatus() {

        TaskStatus taskStatus = TaskStatus.newTaskStatus();

        assertFalse(taskStatus.isClosed());
    }

    @Test
    void shouldCreateClosedTaskStatus() {

        TaskStatus taskStatus = TaskStatus.fromStatus(TaskStatus.Status.CLOSED);

        assertTrue(taskStatus.isClosed());
    }

    @Test
    void shouldCloseTaskStatus() {

        TaskStatus taskStatus = TaskStatus.newTaskStatus();

        assertFalse(taskStatus.isClosed());

        taskStatus.close();
        assertTrue(taskStatus.isClosed());
    }

    @Test
    void shouldReturnTaskStatusValue() {

        TaskStatus taskStatus = TaskStatus.newTaskStatus();

        assertEquals(TaskStatus.Status.OPEN, taskStatus.value());
    }

    @Test
    void shouldReturnTaskStatusIfStatusStringIsValid() {

        String status = "open";
        TaskStatus taskStatus = TaskStatus.fromString(status);

        assertEquals(TaskStatus.Status.OPEN, taskStatus.value());
    }

    @Test
    void shouldThrowInvalidTaskStatusExceptionIfStatusStringIsInvalid() {

        String status = "invalid";

        assertThrows(
                InvalidTaskStatusException.class, () -> {
                    TaskStatus.fromString(status);
                }
        );
    }
}
