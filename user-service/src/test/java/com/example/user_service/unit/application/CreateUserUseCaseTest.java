package com.example.user_service.unit.application;

import com.example.user_service.application.CreateUserUseCase;
import com.example.user_service.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {


    @Mock
    RepositoryPort repositoryPort;

    @InjectMocks
    CreateUserUseCase underTest;


    @Test
    void shouldExecute() {
        User user = new User(
                UserID.newUserID(),
                Username.newUserName("user1"),
                UserEmail.newUserEmail("user1@test.de")
        );

        underTest.execute(user);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        verify(repositoryPort).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();

        assertEquals("user1", savedUser.getUsername().toString());
        assertEquals("user1@test.de", savedUser.getUserEmail().toString());
    }
}
