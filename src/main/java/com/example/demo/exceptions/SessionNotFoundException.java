package com.example.demo.exceptions;

public class SessionNotFoundException extends Throwable {
    //the session you are trying to access is not found.

    public SessionNotFoundException(Integer id) {
        System.out.println(" The session you are trying to access was not found. ID: " + id );
    }
}
