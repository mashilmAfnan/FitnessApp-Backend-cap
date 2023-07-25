package com.example.demo.controllers;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.exceptions.RoomNotFoundException;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.models.Room;
import com.example.demo.services.AmenityService;
import com.example.demo.services.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private RoomService roomService;

    @GetMapping("/find-by-roomNo/{roomNo}")
    public ResponseEntity<List<Room>> findAmenitiesByRoomNo(@PathVariable Integer roomNo) {
        List<Room> rooms = roomService.findRoomByNo(roomNo);
        return ResponseEntity.ok(rooms);
    }
    @GetMapping("/find-all")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> registeredInGyms = roomService.getAllRooms();
        return ResponseEntity.ok(registeredInGyms);
    }
    @PostMapping("/add-room")
    public void RegisterNewAmenity(@RequestBody Room room)
    {
        roomService.RegisterNewRoom(room);
    }

    @PutMapping(path ="/up/{id}")
    public void UpdateRoomAvailability(
            @PathVariable("id") Integer id,
            @RequestParam(required = true) Boolean availability
    ) throws AmenityNotFoundException, RoomNotFoundException {
        roomService.updateRoomAvailability(id, availability);
    }

    @DeleteMapping("/del/{id}")
    public void deleteRoomById(@PathVariable("id") Integer id)
    {
        roomService.deleteRoomById(id);
    }
}

//should user have access to this?



