package com.example.task_service.infrastructure.http;

import com.example.task_service.application.*;
import com.example.task_service.domain.Task;
import com.example.task_service.domain.TaskID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = {"http://localhost:5173"})
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final ListTasksUseCase listTasksUseCase;
    private final FindTaskByIDUseCase findTaskByIDUseCase;
    private final PatchTaskUseCase patchTaskUseCase;

    @PostMapping()
    public ResponseEntity<CreateTaskResponse> createTask(@RequestBody CreateTaskRequest createTaskRequest) {

        Task task = TaskMapper.fromRequest(createTaskRequest);

        this.createTaskUseCase.execute(task);

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
    public ResponseEntity<FindTaskResponse> findTaskByID(@PathVariable String id) {

        TaskID taskID = TaskMapper.toTaskID(id);

        Task task = this.findTaskByIDUseCase.execute(taskID);

        FindTaskResponse response = TaskMapper.toFindTaskResponse(task);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<FindTaskResponse> patchTask(@PathVariable String id,
                                                      @RequestBody PatchTaskRequest patchTaskRequest) {

        PatchTask patchTask = TaskMapper.toPatchTask(id, patchTaskRequest);
        this.patchTaskUseCase.execute(patchTask);


        return ResponseEntity.ok().build();
    }
}
