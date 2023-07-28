package com.example.demo.controllers;

import com.example.demo.DTO.SessionBookingDTO;
import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.exceptions.SessionCannotBeBookedException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Session;
import com.example.demo.models.SessionBooking;
import com.example.demo.models.Subscriber;
import com.example.demo.services.AmenityService;
import com.example.demo.services.SessionBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/session-booking")
public class SessionBookingController {
       private SessionBookingService sessionBookingService;
    @Autowired
    public SessionBookingController(SessionBookingService sessionBookingService) {
        this.sessionBookingService = sessionBookingService;
    }
    @PostMapping("/add-sub-session")
    public void SubscriberBookSession(@RequestBody SessionBookingDTO sessionBookingDTO) throws SessionCannotBeBookedException {
        sessionBookingService.subscriberBookSession(sessionBookingDTO);
    }

//    @GetMapping("/find-by-name/{name}")
//    public ResponseEntity<List<SessionBooking>> findAmenitiesByName(@PathVariable String name) {
//        List<SessionBooking> amenities = sessionBookingService.findAmenityByName(name);
//        return ResponseEntity.ok(amenities);
//    }
//    //add find all and give the user access
//    @PostMapping("/add-amenity")
//    public void RegisterNewAmenity(@RequestBody SessionBooking sessionBooking)
//    {
//
//        sessionBookingService.RegisterNewSessionBooking(sessionBooking);
//    }
//
//    @PutMapping(path ="/update/{id}")
//    public void updateAmenity(
//            @PathVariable("id") Integer id,
//            @RequestParam(required = false) Boolean availability
//    ) throws AmenityNotFoundException {
//        //  System.out.println("\n\n\nHello from update controller");
//        sessionBookingService.updateAmenityAvailability(id, availability);
//    }
//
//    @DeleteMapping("/del/{id}")
//    public void deleteAmenity(@PathVariable("id") Integer id)
//    {
//        sessionBookingService.deleteAmenityById(id);
//    }
}





