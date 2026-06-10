package com.example.task_service.task.infrastructure.http;

import com.example.task_service.task.application.commands.CreateTaskCommand;
import com.example.task_service.task.application.commands.PatchTaskCommand;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.infrastructure.http.request.CreateTaskRequest;
import com.example.task_service.task.infrastructure.http.request.PatchTaskRequest;
import com.example.task_service.task.infrastructure.http.response.CreateTaskResponse;
import com.example.task_service.task.infrastructure.http.response.FindTaskResponse;
import com.example.task_service.task.infrastructure.http.response.ListTasksResponse;
import com.example.task_service.task.infrastructure.http.response.TaskResponse;

import java.util.List;
import java.util.UUID;

public class TaskMapper {

    public static CreateTaskCommand toCreateTaskCommand(CreateTaskRequest createTaskRequest) {
        return new CreateTaskCommand(createTaskRequest.title(), createTaskRequest.projectID());
    }

    public static ListTasksResponse toListTasksResponse(List<Task> tasks) {

        List<TaskResponse> taskResponses = tasks.stream().map(TaskMapper::toTaskResponse).toList();

        return new ListTasksResponse(taskResponses);
    }

    private static TaskResponse toTaskResponse(Task task) {
        return new TaskResponse(
                task.getId().toString(),
                task.getTitle().toString(),
                task.getStatus().value().toString(),
                task.getProjectID().toString()
        );

    }

    public static FindTaskResponse toFindTaskResponse(Task task) {
        return new FindTaskResponse(
                task.getId().toString(),
                task.getTitle().toString(),
                task.getStatus().value().toString(),
                task.getProjectID().id()

        );
    }


    public static PatchTaskCommand toPatchTaskCommand(UUID id, PatchTaskRequest patchTaskRequest) {
        return new PatchTaskCommand(id, patchTaskRequest.title(), patchTaskRequest.status());
    }

    public static CreateTaskResponse toCreateTaskResponse(Task task) {
        return new CreateTaskResponse(
                task.getId().toString(),
                task.getTitle().toString(),
                task.getStatus().value().toString(),
                task.getProjectID().id()
        );
    }

}
