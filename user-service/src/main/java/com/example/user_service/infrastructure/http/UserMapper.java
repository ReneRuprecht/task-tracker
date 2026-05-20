package com.example.user_service.infrastructure.http;

import com.example.user_service.domain.User;
import com.example.user_service.domain.UserEmail;
import com.example.user_service.domain.UserID;
import com.example.user_service.domain.Username;

public class UserMapper {

    public static User toUser(CreateUserRequest createUserRequest) {

        Username username = Username.newUserName(createUserRequest.username());
        UserEmail userEmail = UserEmail.newUserEmail(createUserRequest.email());
        UserID userID = UserID.newUserID();
        return new User(userID, username, userEmail);

    }
}
