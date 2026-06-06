package com.example.task_service.task.unit.domain;

import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskID;
import com.example.task_service.task.domain.TaskName;
import com.example.task_service.task.domain.TaskStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TaskTest {

    @Test
    void shouldCreateTask() {

        TaskID id = TaskID.newTaskID();
        TaskName name = TaskName.newTaskName("add feature");
        TaskStatus status = TaskStatus.newTaskStatus();

        Task task = new Task(id, name, status);

        assertEquals("add feature", task.getName().toString());
        assertNotEquals("", task.getId().toString());
    }

}
