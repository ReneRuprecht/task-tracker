package com.example.task_service.project.integration.infrastructure.http;

import com.example.task_service.project.application.CreateProjectCommand;
import com.example.task_service.project.application.CreateProjectUseCase;
import com.example.task_service.project.application.ListProjectsUseCase;
import com.example.task_service.project.application.ListTasksByProjectIDUseCase;
import com.example.task_service.project.domain.Project;
import com.example.task_service.project.domain.ProjectID;
import com.example.task_service.project.infrastructure.http.ProjectController;
import com.example.task_service.task.domain.Task;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProjectController.class)
@ActiveProfiles("test")
public class ProjectControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CreateProjectUseCase createProjectUseCase;

    @MockitoBean
    ListProjectsUseCase listProjectsUseCase;

    @MockitoBean
    ListTasksByProjectIDUseCase listTasksByProjectIDUseCase;


    @Test
    void shouldCreateProjectWithStatusCreated() throws Exception {

        when(createProjectUseCase.execute(new CreateProjectCommand("My Project"))).thenReturn(
                Project.create("My Project"));

        mockMvc
                .perform(post("/api/v1/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                      "name": "My Project"
                                    }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".name").value("My Project"));
    }


    @Test
    void shouldListProjects() throws Exception {

        Project project1 = Project.create("My Project 1");
        Project project2 = Project.create("My Project 2");

        when(listProjectsUseCase.execute()).thenReturn(List.of(project1, project2));

        mockMvc
                .perform(get("/api/v1/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projects.length()").value(2))
                .andExpect(jsonPath("$.projects[0].name").value("My Project 1"))
                .andExpect(jsonPath("$.projects[1].name").value("My Project 2"));
    }

    @Test
    void shouldListTaskByProject() throws Exception {

        ProjectID id = ProjectID.newProjectID();
        Task task1 = Task.create("task1", id.id());
        Task task2 = Task.create("task2", id.id());

        when(listTasksByProjectIDUseCase.execute(any(ProjectID.class))).thenReturn(List.of(
                task1,
                task2
        ));

        mockMvc
                .perform(get(String.format("/api/v1/projects/%s", id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks.length()").value(2))
                .andExpect(jsonPath("$.tasks[0].title").value("task1"))
                .andExpect(jsonPath("$.tasks[1].title").value("task2"));
    }

}
