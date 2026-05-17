package com.example.task_service.infrastructure.http;

import com.example.task_service.domain.Task;
import com.example.task_service.domain.TaskID;
import com.example.task_service.domain.TaskName;
import com.example.task_service.domain.TaskStatus;

public class TaskMapper {

    public static Task fromRequest(CreateTaskRequest createTaskRequest) {
        TaskID id = TaskID.newTaskID();

        TaskName name = TaskName.newTaskName(createTaskRequest.name());

        TaskStatus status = TaskStatus.newTaskStatus();

        return new Task(id, name, status);
    }
}
