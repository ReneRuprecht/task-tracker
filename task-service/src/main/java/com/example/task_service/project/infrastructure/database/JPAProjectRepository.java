package com.example.task_service.project.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JPAProjectRepository extends JpaRepository<ProjectEntity, UUID> {
}
