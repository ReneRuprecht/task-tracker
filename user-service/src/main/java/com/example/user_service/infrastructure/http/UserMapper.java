package com.example.user_service.infrastructure.http;

import com.example.user_service.application.command.CreateUserCommand;

public class UserMapper {

    public static CreateUserCommand toCreateUserCommand(CreateUserRequest createUserRequest) {

        return new CreateUserCommand(createUserRequest.username(), createUserRequest.email());

    }
}
