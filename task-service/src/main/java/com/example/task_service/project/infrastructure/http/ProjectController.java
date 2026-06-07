package com.example.task_service.project.infrastructure.http;

import com.example.task_service.project.application.CreateProjectCommand;
import com.example.task_service.project.application.CreateProjectUseCase;
import com.example.task_service.project.application.ListProjectsUseCase;
import com.example.task_service.project.domain.Project;
import com.example.task_service.project.infrastructure.http.create.CreateProjectRequest;
import com.example.task_service.project.infrastructure.http.create.CreateProjectResponse;
import com.example.task_service.project.infrastructure.http.list.ListProjectsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;
    private final ListProjectsUseCase listProjectsUseCase;

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

}
