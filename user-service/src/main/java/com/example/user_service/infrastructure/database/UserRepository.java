package com.example.user_service.infrastructure.database;

import com.example.user_service.domain.RepositoryPort;
import com.example.user_service.domain.User;
import com.example.user_service.domain.exception.UserEmailAlreadyExistsException;
import com.example.user_service.domain.exception.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository implements RepositoryPort {

    private final JPAUserRepository jpaUserRepository;

    @Override
    public void save(User user) {

        UserEntity entity = UserMapper.toEntity(user);

        try {
            this.jpaUserRepository.save(entity);

        } catch (DataIntegrityViolationException e) {

            String message = e.getMessage();

            if (message.contains("uk_user_email")) {
                throw new UserEmailAlreadyExistsException();
            }
            if (message.contains("uk_user_name")) {
                throw new UsernameAlreadyExistsException();
            }

            throw new DataIntegrityViolationException("Unexpected DB error");
        }
    }
}
