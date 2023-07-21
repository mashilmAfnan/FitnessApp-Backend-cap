package com.example.demo.exceptions;

public class RoomNotFoundException extends Throwable {
    public RoomNotFoundException(Integer roomNo) {
        System.out.println("Room with number " + roomNo + " was not found.");
    }
}
