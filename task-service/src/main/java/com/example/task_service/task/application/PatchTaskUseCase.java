package com.example.task_service.task.application;

import com.example.task_service.task.application.commands.PatchTaskCommand;
import com.example.task_service.task.domain.*;
import com.example.task_service.task.domain.exception.TaskNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PatchTaskUseCase {

    private final RepositoryPort repository;

    public Task execute(PatchTaskCommand patchTaskCommand) {
        TaskID id = TaskID.of(patchTaskCommand.id());
        Task task = this.repository.findByID(id).orElseThrow(TaskNotFoundException::new);

        if (patchTaskCommand.title().isPresent()) {
            TaskTitle title = TaskTitle.newTaskTitle(patchTaskCommand.title().get());
            task.setTitle(title);
        }

        if (patchTaskCommand.status().isPresent()) {
            TaskStatus status = TaskStatus.fromString(patchTaskCommand.status().get());
            task.setStatus(status);
        }

        return this.repository.save(task);
    }
}
