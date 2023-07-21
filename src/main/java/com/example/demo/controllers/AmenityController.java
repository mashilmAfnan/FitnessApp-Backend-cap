package com.example.demo.controllers;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.services.AmenityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/amenity")
public class AmenityController {
    private AmenityService amenityService;

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<Amenity>> findAmenitiesByName(@PathVariable String name) {
        List<Amenity> amenities = amenityService.findAmenityByName(name);
        return ResponseEntity.ok(amenities);
    }

    @PostMapping("/add-amenity")
    public void RegisterNewAmenity(@RequestBody Amenity amenity)
    {
        amenityService.RegisterNewAmenity(amenity);
    }

    @PutMapping(path ="/update/{id}")
    public void updateAuthor(
            @PathVariable("id") Integer id,
            @RequestParam(required = true) Boolean availability
    ) throws AmenityNotFoundException {
        amenityService.updateAmenityAvailability(id, availability);
    }

    @DeleteMapping("/del/{id}")
    public void deleteAuthor(@PathVariable("id") Integer id)
    {
        amenityService.deleteAmenityById(id);
    }
}





