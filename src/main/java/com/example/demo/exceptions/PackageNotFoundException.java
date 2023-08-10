package com.example.demo.exceptions;

import com.example.demo.constants;

public class PackageNotFoundException extends Exception {
    public PackageNotFoundException(Integer id) { System.out.println(constants.PACKAGE_NOT_FOUND + id);   }
}
