package com.example.demo.controllers;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.exceptions.PlaceNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.PlaceOfService;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.services.AmenityService;
import com.example.demo.services.PlaceOfServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/place")
public class PlaceOfServiceController {
    private PlaceOfServiceService placeOfServiceService;
@Autowired
    public PlaceOfServiceController(PlaceOfServiceService placeOfServiceService) {
        this.placeOfServiceService = placeOfServiceService;
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<PlaceOfService>> findPlaceOfServiceByName(@PathVariable String name) {
        List<PlaceOfService> places = placeOfServiceService.findPlaceOfServiceByName(name);
        return ResponseEntity.ok(places);
    }
    @GetMapping("/find-all")
    public ResponseEntity<List<PlaceOfService>> getAllPlaces() {
        List<PlaceOfService> registeredInGyms = placeOfServiceService.getAllPlaces();
        return ResponseEntity.ok(registeredInGyms);
    }
    @PostMapping("/add-place")
    public void RegisterNewPlace(@RequestBody PlaceOfService placeOfService)
    {
        placeOfServiceService.RegisterNewPlace(placeOfService);
    }
//update place of service
    @PutMapping(path ="/update/{id}")
    public void updatePlaceData(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location
    ) throws PlaceNotFoundException {
        placeOfServiceService.UpdatePlaceData(id, name, location);
    }

    @DeleteMapping("/del/{id}")
    public void deletePlaceById(@PathVariable("id") Integer id)
    {
        placeOfServiceService.deletePlaceById(id);
    }
}





