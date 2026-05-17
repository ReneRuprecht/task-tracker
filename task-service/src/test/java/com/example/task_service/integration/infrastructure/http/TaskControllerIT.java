package com.example.task_service.integration.infrastructure.http;

import com.example.task_service.application.CreateTaskUseCase;
import com.example.task_service.infrastructure.http.TaskController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TaskController.class)
@ActiveProfiles("test")
public class TaskControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CreateTaskUseCase createTaskUseCase;

    @Test
    void shouldCreateTaskWithStatusCreated() throws Exception {


        mockMvc.perform(post("/api/v1/tasks")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("""
                                   {
                                     "name": "My Task"
                                   }
                               """))
               .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnStatus400WithBadRequestData() throws Exception {


        mockMvc.perform(post("/api/v1/tasks")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("""
                                   {
                                     "name": ""
                                   }
                               """))
               .andExpect(status().isBadRequest());
    }


}
