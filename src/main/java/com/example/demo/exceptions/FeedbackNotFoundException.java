package com.example.demo.exceptions;

import com.example.demo.constants;

public class FeedbackNotFoundException extends Throwable {
    public FeedbackNotFoundException(Integer id) {
        System.out.println(constants.FEEDBACK_NOT_FOUND +id);
    }
}
