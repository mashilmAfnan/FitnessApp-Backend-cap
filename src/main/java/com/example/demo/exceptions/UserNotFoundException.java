package com.example.demo.exceptions;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String userNotFound) {
        System.out.println(userNotFound);
    }
}
