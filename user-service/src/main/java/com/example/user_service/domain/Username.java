package com.example.user_service.domain;

import com.example.user_service.domain.exception.InvalidUsernameException;

public class Username {

    private final String username;

    private Username(String username){
        this.username=username;
    }

    public static Username newUserName(String username){
        if (username.isBlank()){
            throw new InvalidUsernameException();
        }
        return new Username(username);
    }

    public String toString(){
        return this.username;
    }
}
