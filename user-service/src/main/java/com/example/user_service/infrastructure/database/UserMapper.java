package com.example.user_service.infrastructure.database;


import com.example.user_service.domain.User;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.id = user.getUserid().value();
        entity.username = user.getUsername().toString();
        entity.email = user.getUserEmail().toString();

        return entity;
    }
}
