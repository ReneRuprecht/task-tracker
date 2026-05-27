package com.example.user_service.integration.infrastructure.database;

import com.example.user_service.domain.User;
import com.example.user_service.domain.UserEmail;
import com.example.user_service.domain.UserID;
import com.example.user_service.domain.Username;
import com.example.user_service.domain.exception.UserEmailAlreadyExistsException;
import com.example.user_service.domain.exception.UsernameAlreadyExistsException;
import com.example.user_service.infrastructure.database.JPAUserRepository;
import com.example.user_service.infrastructure.database.UserEntity;
import com.example.user_service.infrastructure.database.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryIT {

    @Autowired
    JPAUserRepository jpaUserRepository;

    @Autowired
    UserRepository underTest;

    @BeforeEach
    void beforeAll() {
        jpaUserRepository.deleteAll();
    }

    @Test
    void shouldSaveUser() {
        User user = new User(
                UserID.newUserID(),
                Username.newUserName("user1"),
                UserEmail.newUserEmail("user1@test.de")
        );

        underTest.save(user);

        List<UserEntity> entities = jpaUserRepository.findAll();

        assertFalse(entities.isEmpty());

        UserEntity userEntity = entities.getFirst();

        assertFalse(userEntity.getId().toString().isBlank());
        assertEquals("user1", userEntity.getUsername());
        assertEquals("user1@test.de", userEntity.getEmail());
    }

    @Test
    void shouldThrowUsernameAlreadyExistsException() {
        User user = new User(
                UserID.newUserID(),
                Username.newUserName("user1"),
                UserEmail.newUserEmail("user1@test.de")
        );

        underTest.save(user);


        Throwable exception = assertThrows(
                UsernameAlreadyExistsException.class, () -> {
                    User user2 = new User(
                            UserID.newUserID(),
                            Username.newUserName("user1"),
                            UserEmail.newUserEmail("user2@test.de")
                    );

                    underTest.save(user2);
                }
        );

        assertEquals("Username already exists", exception.getMessage());

    }

    @Test
    void shouldThrowUserEmailAlreadyExistsException() {
        User user = new User(
                UserID.newUserID(),
                Username.newUserName("user1"),
                UserEmail.newUserEmail("user1@test.de")
        );

        underTest.save(user);


        Throwable exception = assertThrows(
                UserEmailAlreadyExistsException.class, () -> {
                    User user2 = new User(
                            UserID.newUserID(),
                            Username.newUserName("user2"),
                            UserEmail.newUserEmail("user1@test.de")
                    );

                    underTest.save(user2);
                }
        );

        assertEquals("Email already exists", exception.getMessage());

    }
}
