package com.example.demo.controllers;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.services.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/amenity")
public class AmenityController {
    @Autowired
    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    private AmenityService amenityService;

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<Amenity>> findAmenitiesByName(@PathVariable String name) {
        List<Amenity> amenities = amenityService.findAmenityByName(name);
        return ResponseEntity.ok(amenities);
    }
    @GetMapping("/find-all")
    public ResponseEntity<List<Amenity>> getAllAmenities() {
        List<Amenity> amenities = amenityService.getAllAmenities();
        return ResponseEntity.ok(amenities);
    }
//add find all and give the user access
    @PostMapping("/add-amenity")
    public void RegisterNewAmenity(@RequestBody Amenity amenity)
    {
      //  System.out.println("\n" + amenity + "\n");
        amenityService.RegisterNewAmenity(amenity);
    }

    @PutMapping(path ="/update/{id}")
    public void updateAmenity(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) Boolean availability
    ) throws AmenityNotFoundException {
      //  System.out.println("\n\n\nHello from update controller");
        amenityService.updateAmenityAvailability(id, availability);
    }

    @DeleteMapping("/del/{id}")
    public void deleteAmenity(@PathVariable("id") Integer id)
    {
        amenityService.deleteAmenityById(id);
    }
}





