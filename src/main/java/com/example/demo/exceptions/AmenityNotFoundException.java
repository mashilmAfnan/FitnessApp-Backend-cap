package com.example.demo.exceptions;

import com.example.demo.constants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class AmenityNotFoundException extends Throwable {
    public AmenityNotFoundException(Integer id) { System.out.println(constants.AMENITY_NOT_FOUND + id);    }
}
