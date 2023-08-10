package com.example.demo.exceptions;

import com.example.demo.constants;

public class DiscountNotFoundException extends Exception {

    public DiscountNotFoundException(Integer id) {  System.out.println(constants.DISCOUNT_NOT_FOUND + id);    }
}
