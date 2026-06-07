package com.example.task_service.project.infrastructure.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Table(name = "projects")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProjectEntity {

    @Id
    private UUID id;
    private String name;
}
