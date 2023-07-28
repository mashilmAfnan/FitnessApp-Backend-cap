package com.example.demo.controllers;

import com.example.demo.DTO.SubscriptionDTO;
import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Subscription;
import com.example.demo.services.AmenityService;
import com.example.demo.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//should be linked to user and subscriber and package?
@RestController
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {

    private SubscriptionService subscriptionService;
@Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
//    @GetMapping("/find-by-id/{id}")
//    public ResponseEntity<List<Subscription>> findSubscriptionById(@PathVariable Integer id) {
//        List<Subscription> amenities = subscriptionService.findSubscriptionById(id);
//        return ResponseEntity.ok(amenities);
//    }

    @PostMapping("/add-subscription") //throw an exception for any error that could ocur
    public void registerNewSubscription(@RequestBody SubscriptionDTO subscriptionDTO)
    {

        subscriptionService.addSubscription(subscriptionDTO);
    }



//    @DeleteMapping("/del/{id}")
//    public void deleteSubscriptionById(@PathVariable("id") Integer id)
//    {
//        subscriptionService.deleteSubscriptionById(id);
//    }
}






