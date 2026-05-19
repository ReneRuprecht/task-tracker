package com.example.task_service.application;

import com.example.task_service.domain.*;
import com.example.task_service.domain.exception.TaskNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PatchTaskUseCase {

    private final RepositoryPort repository;

    public void execute(PatchTask patchTask) {
        TaskID id = TaskID.fromString(patchTask.id());
        Task task = this.repository.findByID(id).orElseThrow(TaskNotFoundException::new);


        if (!patchTask.name().isBlank()) {
            TaskName name = TaskName.newTaskName(patchTask.name());
            task.setName(name);
        }

        if (!patchTask.status().isBlank()) {
            TaskStatus status = TaskStatus.fromString(patchTask.status());
            task.setStatus(status);
        }

        this.repository.save(task);
    }
}
