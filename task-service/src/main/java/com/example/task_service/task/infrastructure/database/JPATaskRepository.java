package com.example.task_service.task.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface JPATaskRepository extends JpaRepository<TaskEntity, UUID> {
}
