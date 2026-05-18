package com.example.task_service.domain;

import java.util.List;

public interface RepositoryPort {

    void save(Task task);

    List<Task> findAll();
}
