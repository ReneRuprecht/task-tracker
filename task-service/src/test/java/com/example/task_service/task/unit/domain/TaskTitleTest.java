package com.example.task_service.task.unit.domain;

import com.example.task_service.task.domain.TaskTitle;
import com.example.task_service.task.domain.exception.EmptyTaskTitleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskTitleTest {

    @Test
    void shouldCreateTasktitle() {

        String expected = "add feature";

        TaskTitle actual = TaskTitle.newTaskTitle("add feature");

        assertEquals(expected, actual.toString());
    }

    @Test
    void shouldThrowEmptyTaskTitleException() {

        Exception exception = assertThrows(
                EmptyTaskTitleException.class, () -> {
                    TaskTitle.newTaskTitle("");
                }
        );

        String expectedMessage = "TaskTitle cannot be empty";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}
