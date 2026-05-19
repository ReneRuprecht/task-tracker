package com.example.task_service.unit.infrastructure.http;

import com.example.task_service.application.PatchTask;
import com.example.task_service.domain.Task;
import com.example.task_service.domain.TaskID;
import com.example.task_service.infrastructure.http.CreateTaskRequest;
import com.example.task_service.infrastructure.http.PatchTaskRequest;
import com.example.task_service.infrastructure.http.TaskMapper;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskMapperTest {

    public static PatchTask toPatchTask(String id, PatchTaskRequest patchTaskRequest) {
        return new PatchTask(
                id,
                patchTaskRequest.name().orElse(""),
                patchTaskRequest.status().orElse("")
        );
    }

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
}
