package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class AmenityNotFoundException extends Throwable {


    public AmenityNotFoundException(Integer id) {
        System.out.println("Amenity with ID " + id + " not found.");
    }


}
