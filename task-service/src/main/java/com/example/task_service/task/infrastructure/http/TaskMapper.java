package com.example.task_service.task.infrastructure.http;

import com.example.task_service.task.application.PatchTask;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskID;
import com.example.task_service.task.domain.TaskStatus;
import com.example.task_service.task.domain.TaskTitle;

import java.util.List;

public class TaskMapper {

    public static Task fromRequest(CreateTaskRequest createTaskRequest) {
        TaskID id = TaskID.newTaskID();

        TaskTitle title = TaskTitle.newTaskTitle(createTaskRequest.title());

        TaskStatus status = TaskStatus.newTaskStatus();

        return new Task(id, title, status);
    }

    public static ListTasksResponse toListTasksResponse(List<Task> tasks) {

        List<TaskResponse> taskResponses = tasks.stream().map(TaskMapper::toTaskResponse).toList();

        return new ListTasksResponse(taskResponses);
    }

    private static TaskResponse toTaskResponse(Task task) {
        return new TaskResponse(
                task.getId().toString(),
                task.getTitle().toString(),
                task.getStatus().value().toString()
        );

    }

    public static FindTaskResponse toFindTaskResponse(Task task) {
        return new FindTaskResponse(
                task.getId().toString(),
                task.getTitle().toString(),
                task.getStatus().value().toString()
        );
    }

    public static TaskID toTaskID(String id) {
        return TaskID.fromString(id);
    }

    public static PatchTask toPatchTask(String id, PatchTaskRequest patchTaskRequest) {
        return new PatchTask(
                id,
                patchTaskRequest.title().orElse(""),
                patchTaskRequest.status().orElse("")
        );
    }

    public static CreateTaskResponse toCreateTaskResponse(Task task) {
        return new CreateTaskResponse(
                task.getId().toString(),
                task.getTitle().toString(),
                task.getStatus().value().toString()
        );
    }

}
