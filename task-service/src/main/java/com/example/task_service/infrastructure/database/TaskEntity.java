package com.example.task_service.infrastructure.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
@Setter
public class TaskEntity {
    @Id
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    public enum Status {
        OPEN,
        CLOSED
    }

}
