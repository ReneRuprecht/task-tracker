package com.example.task_service.unit.infrastructure.http;

import com.example.task_service.application.PatchTask;
import com.example.task_service.domain.Task;
import com.example.task_service.domain.TaskID;
import com.example.task_service.domain.TaskName;
import com.example.task_service.domain.TaskStatus;
import com.example.task_service.infrastructure.http.CreateTaskRequest;
import com.example.task_service.infrastructure.http.CreateTaskResponse;
import com.example.task_service.infrastructure.http.PatchTaskRequest;
import com.example.task_service.infrastructure.http.TaskMapper;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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

    @Test
    void shouldMapStringIDAndPatchTaskRequestToPatchTask() {

        TaskID id = TaskID.newTaskID();
        PatchTaskRequest patchTaskRequest = new PatchTaskRequest(
                Optional.of("feature"),
                Optional.of("open")
        );

        PatchTask patchTask = TaskMapper.toPatchTask(id.toString(), patchTaskRequest);

        assertEquals(id.toString(), patchTask.id());
        assertEquals("feature", patchTask.name());
        assertEquals("open", patchTask.status());
    }

    @Test
    void shouldMapTaskToCreateTaskResponse() {

        TaskID id = TaskID.newTaskID();
        TaskName name = TaskName.newTaskName("feature");
        TaskStatus status = TaskStatus.newTaskStatus();
        Task task = new Task(id, name, status);

        CreateTaskResponse createTaskResponse = TaskMapper.toCreateTaskResponse(task);

        assertFalse(createTaskResponse.id().isBlank());
        assertEquals("feature", createTaskResponse.name());
        assertEquals("OPEN", createTaskResponse.status());
    }
}
