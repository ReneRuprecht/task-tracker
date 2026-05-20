package com.example.user_service.infrastructure.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserEntity {

    @Id
    UUID id;

    String username;
    String email;

}
