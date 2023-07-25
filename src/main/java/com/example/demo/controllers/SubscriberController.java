package com.example.demo.controllers;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.models.Subscriber;
import com.example.demo.services.AmenityService;
import com.example.demo.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/subscriber")
public class SubscriberController {
    @Autowired

    private SubscriberService subscriberService;

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<List<Subscriber>> findSubscriberById(@PathVariable Integer id) {
        List<Subscriber> subscribers = subscriberService.findSubscriberById(id).stream().collect(Collectors.toList());
        return ResponseEntity.ok(subscribers);
    }
    //need filtering sub/subscription/ which pt..
    @GetMapping("/find-all")
    public ResponseEntity<List<Subscriber>> getAllSubscribers() {
        List<Subscriber> subscribers = subscriberService.getAllSubscribers();
        return ResponseEntity.ok(subscribers);
    }
    //add find all and give the user access
    @PostMapping("/add-subscriber")
    public void RegisterNewSubscriber(@RequestBody Subscriber subscriber)
    {

        subscriberService.RegisterNewSubscriber(subscriber);
    }

//    @PutMapping(path ="/update/{id}")
//    public void updateAmenity(
//            @PathVariable("id") Integer id,
//            @RequestParam(required = false) Boolean availability
//    ) throws AmenityNotFoundException {
//        //  System.out.println("\n\n\nHello from update controller");
//        subscriberService.updateAmenityAvailability(id, availability);
//    }
//who can delete subscription?
    @DeleteMapping("/del/{id}")
    public void deleteSubscriberById(@PathVariable("id") Integer id)
    {
        subscriberService.deleteSubscriberById(id);
    }
}





