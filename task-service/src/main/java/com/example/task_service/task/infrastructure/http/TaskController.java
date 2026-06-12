package com.example.task_service.task.infrastructure.http;

import com.example.task_service.task.application.CreateTaskUseCase;
import com.example.task_service.task.application.FindTaskByIDUseCase;
import com.example.task_service.task.application.ListTasksUseCase;
import com.example.task_service.task.application.PatchTaskUseCase;
import com.example.task_service.task.application.commands.CreateTaskCommand;
import com.example.task_service.task.application.commands.PatchTaskCommand;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskID;
import com.example.task_service.task.infrastructure.http.request.CreateTaskRequest;
import com.example.task_service.task.infrastructure.http.request.PatchTaskRequest;
import com.example.task_service.task.infrastructure.http.response.CreateTaskResponse;
import com.example.task_service.task.infrastructure.http.response.FindTaskResponse;
import com.example.task_service.task.infrastructure.http.response.ListTasksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
@CrossOrigin(originPatterns = {"http://localhost:*", "http://frontend:*", "http://127.0.0.1:*", "http://172.*:*"})

public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final ListTasksUseCase listTasksUseCase;
    private final FindTaskByIDUseCase findTaskByIDUseCase;
    private final PatchTaskUseCase patchTaskUseCase;

    @PostMapping()
    public ResponseEntity<CreateTaskResponse> createTask(@RequestBody CreateTaskRequest createTaskRequest) {

        CreateTaskCommand createTaskCommand = TaskMapper.toCreateTaskCommand(createTaskRequest);

        Task task = this.createTaskUseCase.execute(createTaskCommand);

        CreateTaskResponse createTaskResponse = TaskMapper.toCreateTaskResponse(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createTaskResponse);
    }

    @GetMapping()
    public ResponseEntity<ListTasksResponse> listTasks() {

        List<Task> tasks = this.listTasksUseCase.execute();

        ListTasksResponse response = TaskMapper.toListTasksResponse(tasks);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<FindTaskResponse> findTaskByID(@PathVariable UUID id) {

        TaskID taskID = TaskID.of(id);

        Task task = this.findTaskByIDUseCase.execute(taskID);

        FindTaskResponse response = TaskMapper.toFindTaskResponse(task);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<FindTaskResponse> patchTask(@PathVariable UUID id,
                                                      @RequestBody PatchTaskRequest patchTaskRequest) {

        PatchTaskCommand patchTaskCommand = TaskMapper.toPatchTaskCommand(id, patchTaskRequest);
        Task task = this.patchTaskUseCase.execute(patchTaskCommand);

        FindTaskResponse response = TaskMapper.toFindTaskResponse(task);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
