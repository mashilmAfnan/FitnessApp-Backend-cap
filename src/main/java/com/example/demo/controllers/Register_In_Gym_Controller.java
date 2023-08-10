package com.example.demo.controllers;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.services.Register_In_Gym_Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class Register_In_Gym_Controller {
    private Register_In_Gym_Service registerInGymService;
//    @GetMapping("/find-by-name/{name}")
//    public ResponseEntity<List<Registered_In_Gym>> findAmenitiesByName(@PathVariable Integer id) {
//        List<Registered_In_Gym> registeredInGyms = registerInGymService.findRegisteredAdminById(id);
//        return ResponseEntity.ok(registeredInGyms);
//    }
    @GetMapping("/find-all")
    public ResponseEntity<List<Registered_In_Gym>> getAllRegisteredAdmins() {
        List<Registered_In_Gym> registeredInGyms = registerInGymService.getAllRegisteredAdmins();
        return ResponseEntity.ok(registeredInGyms);
    }
    //add find all and give the user access
    @PostMapping("/reg-admin")
    public void RegisterNewAmenity(@RequestBody Registered_In_Gym registerAdmin)
    {
        registerInGymService.RegisterNewAdminInGym(registerAdmin);
    }
    @DeleteMapping("/del/{id}")
    public void deleteAmenity(@PathVariable("id") Integer id)
    {
        registerInGymService.deleteRegisteredAdminById(id);
    }
}
