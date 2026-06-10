package com.example.task_service.task.infrastructure.database;

import com.example.task_service.task.domain.RepositoryPort;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskID;
import com.example.task_service.task.domain.TaskProjectID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepository implements RepositoryPort {

    private final JPATaskRepository jpaTaskRepository;

    public Task save(Task task) {
        TaskEntity entity = TaskMapper.toEntity(task);
        TaskEntity saved = jpaTaskRepository.save(entity);
        return TaskMapper.toDomain(saved);
    }

    @Override
    public List<Task> findAll() {
        return jpaTaskRepository.findAll().stream().map(TaskMapper::toDomain).toList();
    }

    @Override
    public Optional<Task> findByID(TaskID id) {
        return jpaTaskRepository.findById(id.id()).map(TaskMapper::toDomain);
    }

    @Override
    public List<Task> listTasksByProjectID(TaskProjectID projectID) {
        return jpaTaskRepository
                .findAllByProjectID(projectID.id())
                .stream()
                .map(TaskMapper::toDomain)
                .toList();
    }

}
