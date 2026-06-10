package com.example.task_service.task.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
public class Task {

    @Getter
    private final TaskID id;

    @Getter
    @Setter
    private TaskTitle title;

    @Getter
    @Setter
    private TaskStatus status;

    @Getter
    private TaskProjectID projectID;

    public static Task create(String title, UUID projectID) {
        TaskID taskID = TaskID.newTaskID();
        TaskTitle taskTitle = TaskTitle.newTaskTitle(title);
        TaskStatus status = TaskStatus.newTaskStatus();
        TaskProjectID taskProjectID = TaskProjectID.of(projectID);

        return new Task(taskID, taskTitle, status, taskProjectID);

    }

}
