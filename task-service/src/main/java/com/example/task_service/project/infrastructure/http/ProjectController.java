package com.example.task_service.project.infrastructure.http;

import com.example.task_service.project.application.CreateProjectCommand;
import com.example.task_service.project.application.CreateProjectUseCase;
import com.example.task_service.project.application.ListProjectsUseCase;
import com.example.task_service.project.application.ListTasksByProjectIDUseCase;
import com.example.task_service.project.domain.Project;
import com.example.task_service.project.domain.ProjectID;
import com.example.task_service.project.infrastructure.http.create.CreateProjectRequest;
import com.example.task_service.project.infrastructure.http.create.CreateProjectResponse;
import com.example.task_service.project.infrastructure.http.list.ListProjectsResponse;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.infrastructure.http.TaskMapper;
import com.example.task_service.task.infrastructure.http.response.ListTasksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
@CrossOrigin(origins = {"http://localhost:5173", "http://frontend"})
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;
    private final ListProjectsUseCase listProjectsUseCase;

    private final ListTasksByProjectIDUseCase listTasksByProjectIDUseCase;

    @PostMapping()
    public ResponseEntity<CreateProjectResponse> createProject(@RequestBody CreateProjectRequest createProjectRequest) {

        CreateProjectCommand createProjectCommand = new CreateProjectCommand(createProjectRequest.name());

        Project project = this.createProjectUseCase.execute(createProjectCommand);

        CreateProjectResponse createProjectResponse = ProjectMapper.toCreateProjectResponse(project);

        return ResponseEntity.status(HttpStatus.CREATED).body(createProjectResponse);
    }

    @GetMapping()
    public ResponseEntity<ListProjectsResponse> listProjects() {

        List<Project> projects = this.listProjectsUseCase.execute();

        ListProjectsResponse listProjectsResponse = ProjectMapper.toListProjectsResponse(projects);

        return ResponseEntity.ok(listProjectsResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<ListTasksResponse> listTasksByProjectID(@PathVariable UUID id) {

        List<Task> tasks = this.listTasksByProjectIDUseCase.execute(ProjectID.of(id));

        ListTasksResponse response = TaskMapper.toListTasksResponse(tasks);

        return ResponseEntity.ok(response);
    }

}
