package com.example.user_service.infrastructure.database;

import com.example.user_service.domain.RepositoryPort;
import com.example.user_service.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository implements RepositoryPort {

    private final JPAUserRepository jpaUserRepository;

    @Override
    public void save(User user) {

        UserEntity entity = UserMapper.toEntity(user);
        this.jpaUserRepository.save(entity);
    }
}
