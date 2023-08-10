package com.example.demo.exceptions;

import com.example.demo.constants;

public class PlaceNotFoundException extends Throwable {
    public PlaceNotFoundException(Integer id) {
            System.out.println(constants.PLACE_NOT_FOUND + id);
    }
}
