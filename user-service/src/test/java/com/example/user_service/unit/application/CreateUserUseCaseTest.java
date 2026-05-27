package com.example.user_service.unit.application;

import com.example.user_service.application.CreateUserUseCase;
import com.example.user_service.application.PublishPort;
import com.example.user_service.application.command.CreateUserCommand;
import com.example.user_service.domain.RepositoryPort;
import com.example.user_service.domain.User;
import com.example.user_service.domain.exception.InvalidUserEmailException;
import com.example.user_service.domain.exception.InvalidUsernameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {


    @Mock
    RepositoryPort repositoryPort;

    @Mock
    PublishPort publishPort;

    @InjectMocks
    CreateUserUseCase underTest;


    @Test
    void shouldExecute() {
        CreateUserCommand createUserCommand = new CreateUserCommand("user1", "user1@test.de");

        underTest.execute(createUserCommand);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        verify(repositoryPort).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();

        assertEquals("user1", savedUser.getUsername().toString());
        assertEquals("user1@test.de", savedUser.getUserEmail().toString());
    }

    @Test
    void shouldThrowInvalidUsernameExceptionIfUsernameIsNull() {
        CreateUserCommand createUserCommand = new CreateUserCommand(null, "user1@test.de");
        assertThrows(
                InvalidUsernameException.class, () -> {
                    underTest.execute(createUserCommand);
                }
        );
    }

    @Test
    void shouldThrowInvalidUsernameExceptionIfUsernameIsBlank() {
        CreateUserCommand createUserCommand = new CreateUserCommand("", "user1@test.de");
        assertThrows(
                InvalidUsernameException.class, () -> {
                    underTest.execute(createUserCommand);
                }
        );
    }


    @Test
    void shouldThrowInvalidUserEmailExceptionIfEmailIsNull() {
        CreateUserCommand createUserCommand = new CreateUserCommand("user1", null);
        assertThrows(
                InvalidUserEmailException.class, () -> {
                    underTest.execute(createUserCommand);
                }
        );
    }

    @Test
    void shouldThrowInvalidUserEmailExceptionIfEmailIsBlank() {
        CreateUserCommand createUserCommand = new CreateUserCommand("user1", "");
        assertThrows(
                InvalidUserEmailException.class, () -> {
                    underTest.execute(createUserCommand);
                }
        );
    }
}
