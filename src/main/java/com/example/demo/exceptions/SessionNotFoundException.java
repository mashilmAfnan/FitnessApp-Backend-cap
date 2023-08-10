package com.example.demo.exceptions;

import com.example.demo.constants;

public class SessionNotFoundException extends Throwable {
        public SessionNotFoundException(Integer id) {  System.out.println(constants.SESSION_NOT_FOUND + id );
    }
}
