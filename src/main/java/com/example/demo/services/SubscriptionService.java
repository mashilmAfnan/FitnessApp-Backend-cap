//package com.example.demo.services;
//
//import com.example.demo.exceptions.AmenityNotFoundException;
//import com.example.demo.models.Amenity;
//import com.example.demo.models.Subscription;
//import com.example.demo.repositories.AmenityRepo;
//import com.example.demo.repositories.SubscriptionRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.beans.Transient;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static java.lang.Character.getType;
//
//@Service
//public class SubscriptionService {
//    private SubscriptionRepo subscriptionRepo;
//    @Autowired
//
//    public List<Subscription> findSubscriptionById(Integer id) {
//        return subscriptionRepo.findSubscriptionById(id).stream().collect(Collectors.toList());
//    }
////register what about gettign all subscriptions??
////    public void RegisterNewSubscription(Subscription subscription) {
////
////        //System.out.println("In service\n" + amenity + "\n");
////
////        subscriptionRepo.save(amenity);
////    }
//
//
//    public void deleteSubscriptionById(Integer id) {
//
//        boolean exists = subscriptionRepo.existsById(id);
//        if (!exists) {
//            throw new IllegalStateException("book with id " + id + " does not even exist ");
//        }
//        subscriptionRepo.deleteById(id);
//    }
//}
