package com.example.demo.controllers;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.exceptions.RoomNotFoundException;
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

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<Room>> findAmenitiesByRoomNo(@PathVariable Integer roomNo) {
        List<Room> rooms = roomService.findRoomByNo(roomNo);
        return ResponseEntity.ok(rooms);
    }

    @PostMapping("/add-room")
    public void RegisterNewAmenity(@RequestBody Room room)
    {
        roomService.RegisterNewRoom(room);
    }

    @PutMapping(path ="/up/{id}")
    public void updateAuthor(
            @PathVariable("id") Integer id,
            @RequestParam(required = true) Boolean availability
    ) throws AmenityNotFoundException, RoomNotFoundException {
        roomService.updateRoomAvailability(id, availability);
    }

    @DeleteMapping("/del/{id}")
    public void deleteAuthor(@PathVariable("id") Integer id)
    {
        roomService.deleteRoomById(id);
    }
}





