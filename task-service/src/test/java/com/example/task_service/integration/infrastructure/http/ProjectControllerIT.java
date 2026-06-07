package com.example.task_service.integration.infrastructure.http;

import com.example.task_service.project.application.CreateProjectCommand;
import com.example.task_service.project.application.CreateProjectUseCase;
import com.example.task_service.project.application.ListProjectsUseCase;
import com.example.task_service.project.domain.Project;
import com.example.task_service.project.infrastructure.http.ProjectController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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

    @Test
    void shouldCreateProjectWithStatusCreated() throws Exception {

        when(createProjectUseCase.execute(new CreateProjectCommand("My Project"))).thenReturn(
                Project.create("My Project"));

        mockMvc.perform(post("/api/v1/projects").contentType(MediaType.APPLICATION_JSON).content("""
                           {
                             "name": "My Project"
                           }
                       """)).andExpect(status().isCreated())
               .andExpect(jsonPath(".name").value("My Project"));
    }


    @Test
    void shouldListTasks() throws Exception {

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

    /*
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
                TaskTitle.newTaskTitle("feature"),
                TaskStatus.newTaskStatus()
        );

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

        Task task1 = new Task(
                TaskID.newTaskID(),
                TaskTitle.newTaskTitle("feature"),
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
                TaskTitle.newTaskTitle("feature"),
                TaskStatus.newTaskStatus()
        );

        mockMvc
                .perform(patch(String.format("/api/v1/tasks/%s", task1.getId().toString()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                      "title": "refactor",
                                      "status": "closed"
                                    }
                                """))
                .andExpect(status().isOk());
    }
*/

}
