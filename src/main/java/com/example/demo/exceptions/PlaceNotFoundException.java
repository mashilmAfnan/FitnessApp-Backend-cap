package com.example.demo.exceptions;

public class PlaceNotFoundException extends Throwable {
    public PlaceNotFoundException(Integer id) {
            System.out.println("Place with ID " + id + " was not found.");
    }
}
