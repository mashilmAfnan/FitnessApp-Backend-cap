package com.example.demo.repositories;


import com.example.demo.models.PlaceOfService;
import com.example.demo.models.Registered_In_Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface Registered_In_GymRepo extends JpaRepository<Registered_In_Gym, Integer> {
    Optional<Registered_In_Gym> findById(Integer Id);
}
//find all