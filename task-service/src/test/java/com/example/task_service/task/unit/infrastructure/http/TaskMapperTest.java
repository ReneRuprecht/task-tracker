package com.example.task_service.task.unit.infrastructure.http;

import com.example.task_service.task.application.PatchTask;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskID;
import com.example.task_service.task.domain.TaskTitle;
import com.example.task_service.task.domain.TaskStatus;
import com.example.task_service.task.infrastructure.http.CreateTaskRequest;
import com.example.task_service.task.infrastructure.http.CreateTaskResponse;
import com.example.task_service.task.infrastructure.http.PatchTaskRequest;
import com.example.task_service.task.infrastructure.http.TaskMapper;
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
        assertEquals("feature", task.getTitle().toString());
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
        assertEquals("feature", patchTask.title());
        assertEquals("open", patchTask.status());
    }

    @Test
    void shouldMapTaskToCreateTaskResponse() {

        TaskID id = TaskID.newTaskID();
        TaskTitle title = TaskTitle.newTaskTitle("feature");
        TaskStatus status = TaskStatus.newTaskStatus();
        Task task = new Task(id, title, status);

        CreateTaskResponse createTaskResponse = TaskMapper.toCreateTaskResponse(task);

        assertFalse(createTaskResponse.id().isBlank());
        assertEquals("feature", createTaskResponse.title());
        assertEquals("OPEN", createTaskResponse.status());
    }
}
