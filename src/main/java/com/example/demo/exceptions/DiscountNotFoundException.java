package com.example.demo.exceptions;

public class DiscountNotFoundException extends Exception {

    public DiscountNotFoundException(Integer id) {
        System.out.println("Discount with ID " + id + " was not found.");
    }
}
