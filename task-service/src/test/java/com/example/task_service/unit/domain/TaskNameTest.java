package com.example.task_service.unit.domain;

import com.example.task_service.domain.TaskName;
import com.example.task_service.domain.exception.EmptyTaskNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskNameTest {

    @Test
    void shouldCreateTaskName() {

        String expected = "add feature";

        TaskName actual = TaskName.newTaskName("add feature");

        assertEquals(expected, actual.toString());
    }

    @Test
    void shouldThrowEmptyTaskNameException() {

        Exception exception = assertThrows(
                EmptyTaskNameException.class, () -> {
                    TaskName.newTaskName("");
                }
        );

        String expectedMessage = "TaskName cannot be empty";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}
