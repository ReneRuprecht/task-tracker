package com.example.task_service.task.infrastructure.database;

import com.example.task_service.task.domain.RepositoryPort;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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
    public Optional<Task> findByID(TaskID id) {
        UUID uuidID = UUID.fromString(id.toString());
        return jpaTaskRepository.findById(uuidID).map(TaskMapper::fromEntity);

    }

}
