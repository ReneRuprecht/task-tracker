package com.example.task_service.infrastructure.http;

import com.example.task_service.application.CreateTaskUseCase;
import com.example.task_service.application.ListTasksUseCase;
import com.example.task_service.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final ListTasksUseCase listTasksUseCase;

    @PostMapping()
    public ResponseEntity<Void> createTask(@RequestBody CreateTaskRequest createTaskRequest) {

        Task task = TaskMapper.fromRequest(createTaskRequest);

        this.createTaskUseCase.execute(task);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<ListTasksResponse> listTasks() {

        List<Task> tasks = this.listTasksUseCase.execute();

        ListTasksResponse response = TaskMapper.toListTasksResponse(tasks);

        return ResponseEntity.ok(response);
    }
}
