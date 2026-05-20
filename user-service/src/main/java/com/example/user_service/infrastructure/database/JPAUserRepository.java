package com.example.user_service.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JPAUserRepository extends JpaRepository<UserEntity, UUID> {
}
