package com.example.task_service.task.domain;

import java.util.List;
import java.util.Optional;

public interface RepositoryPort {

    void save(Task task);

    List<Task> findAll();

    Optional<Task> findByID(TaskID id);
}
