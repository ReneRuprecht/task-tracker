package com.example.task_service.domain;

import com.example.task_service.domain.exception.TaskNotFoundException;

import java.util.List;

public interface RepositoryPort {

    void save(Task task);

    List<Task> findAll();

    Task findByID(TaskID id) throws TaskNotFoundException;
}
