package com.example.task_service.task.infrastructure.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
@Getter
@Setter
public class TaskEntity {
    @Id
    private UUID id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Status status;
    public enum Status {
        OPEN,
        CLOSED
    }

}
