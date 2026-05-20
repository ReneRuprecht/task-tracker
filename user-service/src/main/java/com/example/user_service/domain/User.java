package com.example.user_service.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class User {

    @Getter
    UserID userid;
    @Getter
    Username username;
    @Getter
    UserEmail userEmail;

}
