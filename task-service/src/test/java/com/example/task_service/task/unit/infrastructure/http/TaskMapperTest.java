package com.example.task_service.task.unit.infrastructure.http;

import com.example.task_service.task.application.commands.CreateTaskCommand;
import com.example.task_service.task.application.commands.PatchTaskCommand;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.infrastructure.http.TaskMapper;
import com.example.task_service.task.infrastructure.http.request.CreateTaskRequest;
import com.example.task_service.task.infrastructure.http.request.PatchTaskRequest;
import com.example.task_service.task.infrastructure.http.response.CreateTaskResponse;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TaskMapperTest {

    @Test
    void shouldMapCreateTaskRequestToCreateTaskCommand() {

        UUID projectID = UUID.randomUUID();
        CreateTaskRequest createTaskRequest = new CreateTaskRequest("feature", projectID);

        CreateTaskCommand createTaskCommand = TaskMapper.toCreateTaskCommand(createTaskRequest);

        assertFalse(createTaskCommand.title().isBlank());
        assertNotNull(createTaskCommand.projectID());
    }

    @Test
    void shouldMapPatchTaskRequestToPatchTaskCommand() {

        UUID id = UUID.randomUUID();
        PatchTaskRequest patchTaskRequest = new PatchTaskRequest(
                Optional.of("feature"),
                Optional.of("open")
        );

        PatchTaskCommand patchTaskCommand = TaskMapper.toPatchTaskCommand(id, patchTaskRequest);

        assertEquals(id.toString(), patchTaskCommand.id().toString());
        assertTrue(patchTaskCommand.title().isPresent());
        assertEquals("feature", patchTaskCommand.title().get());
        assertTrue(patchTaskCommand.status().isPresent());
        assertEquals("open", patchTaskCommand.status().get());
    }

    @Test
    void shouldMapTaskToCreateTaskResponse() {

        String title = "feature";
        UUID projectID = UUID.randomUUID();
        Task task = Task.create(title, projectID);

        CreateTaskResponse createTaskResponse = TaskMapper.toCreateTaskResponse(task);

        assertFalse(createTaskResponse.id().isBlank());
        assertEquals("feature", createTaskResponse.title());
        assertEquals("OPEN", createTaskResponse.status());
    }
}
