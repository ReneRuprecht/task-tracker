package com.example.task_service.integration.infrastructure.http;

import com.example.task_service.application.CreateTaskUseCase;
import com.example.task_service.application.FindTaskByIDUseCase;
import com.example.task_service.application.ListTasksUseCase;
import com.example.task_service.application.PatchTaskUseCase;
import com.example.task_service.domain.Task;
import com.example.task_service.domain.TaskID;
import com.example.task_service.domain.TaskName;
import com.example.task_service.domain.TaskStatus;
import com.example.task_service.domain.exception.TaskNotFoundException;
import com.example.task_service.infrastructure.http.TaskController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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


        mockMvc.perform(post("/api/v1/tasks").contentType(MediaType.APPLICATION_JSON).content("""
                    {
                      "name": "My Task"
                    }
                """)).andExpect(status().isCreated());
    }

    @Test
    void shouldReturnStatus400WithBadRequestData() throws Exception {


        mockMvc.perform(post("/api/v1/tasks").contentType(MediaType.APPLICATION_JSON).content("""
                    {
                      "name": ""
                    }
                """)).andExpect(status().isBadRequest());
    }

    @Test
    void shouldListTasks() throws Exception {

        Task task1 = new Task(
                TaskID.newTaskID(),
                TaskName.newTaskName("feature"),
                TaskStatus.newTaskStatus()
        );
        Task task2 = new Task(
                TaskID.newTaskID(),
                TaskName.newTaskName("refactor"),
                TaskStatus.newTaskStatus()
        );

        when(listTasksUseCase.execute()).thenReturn(List.of(task1, task2));

        mockMvc
                .perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks.length()").value(2))
                .andExpect(jsonPath("$.tasks[0].name").value("feature"))
                .andExpect(jsonPath("$.tasks[1].name").value("refactor"));
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

        Task task1 = new Task(
                TaskID.newTaskID(),
                TaskName.newTaskName("feature"),
                TaskStatus.newTaskStatus()
        );

        when(findTaskByIDUseCase.execute(any())).thenReturn(task1);

        mockMvc
                .perform(get(String.format("/api/v1/tasks/%s", task1.getId().toString())))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value(task1.getId().toString()))
                .andExpect(jsonPath(".name").value("feature"))
                .andExpect(jsonPath(".status").value("OPEN"));
    }

    @Test
    void shouldReturnStatus404IfFindTaskByIDIsEmpty() throws Exception {

        Task task1 = new Task(
                TaskID.newTaskID(),
                TaskName.newTaskName("feature"),
                TaskStatus.newTaskStatus()
        );

        when(findTaskByIDUseCase.execute(any())).thenThrow(TaskNotFoundException.class);

        mockMvc
                .perform(get(String.format("/api/v1/tasks/%s", task1.getId().toString())))
                .andExpect(status().isNotFound());
    }


    @Test
    void shouldPatchTask() throws Exception {

        Task task1 = new Task(
                TaskID.newTaskID(),
                TaskName.newTaskName("feature"),
                TaskStatus.newTaskStatus()
        );

        mockMvc
                .perform(patch(String.format("/api/v1/tasks/%s", task1.getId().toString()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                      "name": "refactor",
                                      "status": "closed"
                                    }
                                """))
                .andExpect(status().isOk());
    }

}
