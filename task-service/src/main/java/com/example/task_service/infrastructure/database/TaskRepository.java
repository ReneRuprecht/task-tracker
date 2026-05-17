package com.example.task_service.infrastructure.database;

import com.example.task_service.domain.RepositoryPort;
import com.example.task_service.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TaskRepository implements RepositoryPort {

    private final JPATaskRepository jpaTaskRepository;

    public void save(Task task) {
        TaskEntity entity = TaskMapper.fromDomain(task);
        this.jpaTaskRepository.save(entity);
    }

}
