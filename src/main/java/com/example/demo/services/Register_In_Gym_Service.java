package com.example.demo.services;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.repositories.AmenityRepo;
import com.example.demo.repositories.Registered_In_GymRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

import static java.lang.Character.getType;

@Service
public class Register_In_Gym_Service {
    private Registered_In_GymRepo registeredInGymRepo;
    @Autowired
    public Register_In_Gym_Service(Registered_In_GymRepo registeredInGymRepo) {
        this.registeredInGymRepo = registeredInGymRepo;
    }



    public List<Registered_In_Gym> getAllRegisteredAdmins() {
        return registeredInGymRepo.findAll();
    }

    //reg by id
    public void RegisterNewAdminInGym(Registered_In_Gym registeredInGym) {

       // System.out.println("In service\n" + amenity + "\n");

        registeredInGymRepo.save(registeredInGym);
    }

    public void deleteRegisteredAdminById(Integer id) {

        boolean exists = registeredInGymRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No admin with this id " + id + " is registered. ");
        }
        registeredInGymRepo.deleteById(id);
    }
}
