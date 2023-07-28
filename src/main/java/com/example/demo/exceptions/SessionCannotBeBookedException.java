package com.example.demo.exceptions;

import com.example.demo.constants;

public class SessionCannotBeBookedException extends Throwable {
    public SessionCannotBeBookedException() {
        System.out.println(constants.SESSION_CANNOT_BE_BOOKED);
    }
}
