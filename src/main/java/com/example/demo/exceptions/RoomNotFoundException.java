package com.example.demo.exceptions;

import com.example.demo.constants;

public class RoomNotFoundException extends Throwable {
    public RoomNotFoundException(Integer roomNo) { System.out.println(constants.ROOM_NOT_FOUND + roomNo);   }
}
