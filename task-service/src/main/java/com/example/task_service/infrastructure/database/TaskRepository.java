package com.example.task_service.infrastructure.database;

import com.example.task_service.domain.RepositoryPort;
import com.example.task_service.domain.Task;
import com.example.task_service.domain.TaskID;
import com.example.task_service.domain.exception.TaskNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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

    @Override
    public Task findByID(TaskID id) {
        UUID uuidID = UUID.fromString(id.toString());
        TaskEntity entity = jpaTaskRepository
                .findById(uuidID)
                .orElseThrow(TaskNotFoundException::new);

        return TaskMapper.fromEntity(entity);
    }

}
