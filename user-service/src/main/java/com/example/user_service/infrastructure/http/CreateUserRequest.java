package com.example.user_service.infrastructure.http;

import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(

        @NotNull(message = "username is required")
        String username,

        @NotNull(message = "email is required")
        String email) {

}