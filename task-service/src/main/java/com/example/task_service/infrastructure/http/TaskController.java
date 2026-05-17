package com.example.task_service.infrastructure.http;

import com.example.task_service.application.CreateTaskUseCase;
import com.example.task_service.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;

    @PostMapping()
    public ResponseEntity<Void> createTask(@RequestBody CreateTaskRequest createTaskRequest) {

        Task task = TaskMapper.fromRequest(createTaskRequest);

        this.createTaskUseCase.execute(task);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
