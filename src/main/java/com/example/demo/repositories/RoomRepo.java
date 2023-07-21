package com.example.demo.repositories;


import com.example.demo.models.PlaceOfService;
import com.example.demo.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {
   // @Query()
    Optional<Room> findByRoomNo(Integer RoomNo);
}
