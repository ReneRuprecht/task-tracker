package com.example.task_service.infrastructure.http;

import com.example.task_service.application.PatchTask;
import com.example.task_service.domain.Task;
import com.example.task_service.domain.TaskID;
import com.example.task_service.domain.TaskName;
import com.example.task_service.domain.TaskStatus;

import java.util.List;

public class TaskMapper {

    public static Task fromRequest(CreateTaskRequest createTaskRequest) {
        TaskID id = TaskID.newTaskID();

        TaskName name = TaskName.newTaskName(createTaskRequest.name());

        TaskStatus status = TaskStatus.newTaskStatus();

        return new Task(id, name, status);
    }

    public static ListTasksResponse toListTasksResponse(List<Task> tasks) {

        List<TaskResponse> taskResponses = tasks.stream().map(TaskMapper::toTaskResponse).toList();

        return new ListTasksResponse(taskResponses);
    }

    private static TaskResponse toTaskResponse(Task task) {
        return new TaskResponse(
                task.getId().toString(),
                task.getName().toString(),
                task.getStatus().value().toString()
        );

    }

    public static FindTaskResponse toFindTaskResponse(Task task) {
        return new FindTaskResponse(
                task.getId().toString(),
                task.getName().toString(),
                task.getStatus().value().toString()
        );
    }

    public static TaskID toTaskID(String id) {
        return TaskID.fromString(id);
    }

    public static PatchTask toPatchTask(String id, PatchTaskRequest patchTaskRequest) {
        return new PatchTask(
                id,
                patchTaskRequest.name().orElse(""),
                patchTaskRequest.status().orElse("")
        );
    }

}
