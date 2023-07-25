package com.example.demo.services;

import com.example.demo.exceptions.RoomNotFoundException;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.models.Room;
import com.example.demo.repositories.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class RoomService {
    private RoomRepo roomRepo;

@Autowired
    public RoomService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    public List<Room> findRoomByNo(Integer roomNo) {
        return roomRepo.findByRoomNo(roomNo) .stream()
                .collect(Collectors.toList());
    }
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    public void RegisterNewRoom(Room room) {

    roomRepo.save(room);
    }
@Transactional
    public void updateRoomAvailability(Integer roomNo, Boolean availability) throws RoomNotFoundException {
        Optional<Room> optionalRoom = roomRepo.findById(roomNo);

        if (optionalRoom.isPresent()) {
            Room amenity = optionalRoom.get();
            amenity.setAvailability(availability);
            roomRepo.save(amenity);
        } else {

            throw new RoomNotFoundException(roomNo);
        }
    }

    public void deleteRoomById(Integer id) {
        boolean exists = roomRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("book with id " + id + " does not even exist ");
        }
        roomRepo.deleteById(id);
    }


}


