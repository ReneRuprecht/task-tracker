package com.example.task_service.task.application;

import com.example.task_service.task.domain.*;
import com.example.task_service.task.domain.exception.TaskNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PatchTaskUseCase {

    private final RepositoryPort repository;

    public void execute(PatchTask patchTask) {
        TaskID id = TaskID.fromString(patchTask.id());
        Task task = this.repository.findByID(id).orElseThrow(TaskNotFoundException::new);


        if (!patchTask.title().isBlank()) {
            TaskTitle title = TaskTitle.newTaskTitle(patchTask.title());
            task.setTitle(title);
        }

        if (!patchTask.status().isBlank()) {
            TaskStatus status = TaskStatus.fromString(patchTask.status());
            task.setStatus(status);
        }

        this.repository.save(task);
    }
}
