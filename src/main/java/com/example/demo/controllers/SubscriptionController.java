//package com.example.demo.controllers;
//
//import com.example.demo.exceptions.AmenityNotFoundException;
//import com.example.demo.models.Amenity;
//import com.example.demo.models.Subscription;
//import com.example.demo.services.AmenityService;
//import com.example.demo.services.SubscriptionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
////should be linked to user and subscriber and package?
//@RestController
//@RequestMapping("/api/v1/amenity")
//public class SubscriptionController {
//    @Autowired
//
//    private SubscriptionService subscriptionService;
//
//    @GetMapping("/find-by-name/{name}")
//    public ResponseEntity<List<Subscription>> findSubscriptionById(@PathVariable Integer id) {
//        List<Subscription> amenities = subscriptionService.findSubscriptionById(id);
//        return ResponseEntity.ok(amenities);
//    }
//
////    @PostMapping("/add-amenity")
////    public void RegisterNewSubscription(@RequestBody Subscription subscription)
////    {
////
////        subscriptionService.RegisterNewSubscription(subscription);
////    }
//
//
//
//    @DeleteMapping("/del/{id}")
//    public void deleteSubscriptionById(@PathVariable("id") Integer id)
//    {
//        subscriptionService.deleteSubscriptionById(id);
//    }
//}
//
//
//
//
//
