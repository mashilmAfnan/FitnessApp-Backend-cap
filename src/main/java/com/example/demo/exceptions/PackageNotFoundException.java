package com.example.demo.exceptions;

public class PackageNotFoundException extends Exception {

    public PackageNotFoundException(Integer id) {

        System.out.println("Package with ID " + id + " not found.");
    }
}
