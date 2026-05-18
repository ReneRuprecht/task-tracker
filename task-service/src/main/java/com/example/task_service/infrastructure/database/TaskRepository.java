package com.example.task_service.infrastructure.database;

import com.example.task_service.domain.RepositoryPort;
import com.example.task_service.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskRepository implements RepositoryPort {

    private final JPATaskRepository jpaTaskRepository;

    public void save(Task task) {
        TaskEntity entity = TaskMapper.fromDomain(task);
        this.jpaTaskRepository.save(entity);
    }

    @Override
    public List<Task> findAll() {
        return jpaTaskRepository.findAll().stream().map(TaskMapper::fromEntity).toList();
    }

}
