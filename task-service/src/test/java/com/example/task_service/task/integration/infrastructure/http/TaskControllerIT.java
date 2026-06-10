package com.example.task_service.task.integration.infrastructure.http;

import com.example.task_service.project.application.exception.ProjectNotFoundException;
import com.example.task_service.task.application.CreateTaskUseCase;
import com.example.task_service.task.application.FindTaskByIDUseCase;
import com.example.task_service.task.application.ListTasksUseCase;
import com.example.task_service.task.application.PatchTaskUseCase;
import com.example.task_service.task.application.commands.CreateTaskCommand;
import com.example.task_service.task.application.commands.PatchTaskCommand;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.exception.EmptyTaskTitleException;
import com.example.task_service.task.domain.exception.TaskNotFoundException;
import com.example.task_service.task.infrastructure.http.TaskController;
import com.example.task_service.task.infrastructure.http.request.CreateTaskRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TaskController.class)
@ActiveProfiles("test")
public class TaskControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CreateTaskUseCase createTaskUseCase;

    @MockitoBean
    ListTasksUseCase listTasksUseCase;

    @MockitoBean
    FindTaskByIDUseCase findTaskByIDUseCase;

    @MockitoBean
    PatchTaskUseCase patchTaskUseCase;

    @Test
    void shouldCreateTaskWithStatusCreated() throws Exception {
        UUID projectID = UUID.randomUUID();
        Task task = Task.create("My Task", projectID);

        CreateTaskRequest createTaskRequest = new CreateTaskRequest("My Task", projectID);
        when(createTaskUseCase.execute(new CreateTaskCommand(
                "My Task",
                projectID
        ))).thenReturn(task);

        ObjectMapper mapper = new ObjectMapper();
        mockMvc
                .perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createTaskRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".title").value("My Task"))
                .andExpect(jsonPath(".status").value("OPEN"));
    }

    @Test
    void shouldReturnStatus400WithBadRequestData() throws Exception {

        UUID projectID = UUID.randomUUID();

        CreateTaskRequest createTaskRequest = new CreateTaskRequest("", projectID);
        ObjectMapper mapper = new ObjectMapper();

        when(createTaskUseCase.execute(any())).thenThrow(EmptyTaskTitleException.class);

        mockMvc
                .perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createTaskRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnStatus404WhenProjectNotExist() throws Exception {

        UUID projectID = UUID.randomUUID();

        CreateTaskRequest createTaskRequest = new CreateTaskRequest("", projectID);
        ObjectMapper mapper = new ObjectMapper();

        when(createTaskUseCase.execute(any())).thenThrow(ProjectNotFoundException.class);

        mockMvc
                .perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createTaskRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldListTasks() throws Exception {

        UUID p1ID = UUID.randomUUID();
        Task task1 = Task.create("feature", p1ID);

        UUID p2ID = UUID.randomUUID();
        Task task2 = Task.create("refactor", p2ID);

        when(listTasksUseCase.execute()).thenReturn(List.of(task1, task2));

        mockMvc
                .perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks.length()").value(2))
                .andExpect(jsonPath("$.tasks[0].title").value("feature"))
                .andExpect(jsonPath("$.tasks[1].title").value("refactor"));
    }

    @Test
    void shouldListTasksWithEmptyList() throws Exception {

        when(listTasksUseCase.execute()).thenReturn(List.of());

        mockMvc
                .perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks.length()").value(0));
    }

    @Test
    void shouldFindTaskByID() throws Exception {

        UUID p1ID = UUID.randomUUID();
        Task task1 = Task.create("feature", p1ID);

        when(findTaskByIDUseCase.execute(any())).thenReturn(task1);

        mockMvc
                .perform(get(String.format("/api/v1/tasks/%s", task1.getId().toString())))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value(task1.getId().toString()))
                .andExpect(jsonPath(".title").value("feature"))
                .andExpect(jsonPath(".status").value("OPEN"));
    }

    @Test
    void shouldReturnStatus404IfFindTaskByIDIsEmpty() throws Exception {

        UUID p1ID = UUID.randomUUID();
        Task task1 = Task.create("feature", p1ID);

        when(findTaskByIDUseCase.execute(any())).thenThrow(TaskNotFoundException.class);

        mockMvc
                .perform(get(String.format("/api/v1/tasks/%s", task1.getId().toString())))
                .andExpect(status().isNotFound());
    }


    @Test
    void shouldPatchTask() throws Exception {

        UUID p1ID = UUID.randomUUID();
        Task task1 = Task.create("feature", p1ID);
        task1.getStatus().close();

        when(patchTaskUseCase.execute(any(PatchTaskCommand.class))).thenReturn(task1);
        mockMvc
                .perform(patch(String.format("/api/v1/tasks/%s", task1.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                      "title": "feature",
                                      "status": "closed"
                                    }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("feature"))
                .andExpect(jsonPath("$.status").value("CLOSED"));
    }

}
