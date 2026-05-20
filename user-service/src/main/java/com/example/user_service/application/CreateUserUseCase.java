package com.example.user_service.application;

import com.example.user_service.application.command.CreateUserCommand;
import com.example.user_service.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final RepositoryPort repositoryPort;

    public void execute(CreateUserCommand createUserCommand) {

        Username username = Username.newUserName(createUserCommand.username());
        UserEmail userEmail = UserEmail.newUserEmail(createUserCommand.email());
        UserID userID = UserID.newUserID();
        User user = new User(userID, username, userEmail);

        this.repositoryPort.save(user);
    }

}
