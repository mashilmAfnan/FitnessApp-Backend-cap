//package com.example.demo.services;
//
//import com.example.demo.exceptions.AmenityNotFoundException;
//import com.example.demo.models.Amenity;
//import com.example.demo.models.SessionBooking;
//import com.example.demo.repositories.AmenityRepo;
//import com.example.demo.repositories.SessionBookingRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.beans.Transient;
//import java.util.List;
//import java.util.Optional;
//
//import static java.lang.Character.getType;
//
//@Service
//public class SessionBookingService {
//    private SessionBookingRepo sessionBookingRepo;
//    @Autowired
//
//    public List<Amenity> findAmenityByName(String name) {
//        return sessionBookingRepo.findAmenityByAmenityType(name);
//    }
//
//    public void RegisterNewAmenity(Amenity amenity) {
//
//        System.out.println("In service\n" + amenity + "\n");
//
//        sessionBookingRepo.save(amenity);
//    }
//    @Transactional
//    public void updateAmenityAvailability(Integer id, Boolean availability) throws AmenityNotFoundException {
//        Optional<SessionBooking> optionalAmenity = sessionBookingRepo.findById(id);
//        System.out.println("\n\n\nHello from update service");
//        if (optionalAmenity.isPresent()) {
//            SessionBooking amenity = optionalAmenity.get();
////            System.out.println("\n\n\n amenity: " +amenity);
////            System.out.println("\n\n\n availability: " +availability);
////            System.out.println((getType(availability)));
//           // amenity.setAvailability(availability);
//            sessionBookingRepo.save(amenity);
//        } else {
//
//            throw new AmenityNotFoundException(id);
//        }
//    }
//
//    public void deleteSessionBookingById(Integer id) {
//
//        boolean exists = sessionBookingRepo.existsById(id);
//        if (!exists) {
//            throw new IllegalStateException("book with id " + id + " does not even exist ");
//        }
//        sessionBookingRepo.deleteById(id);
//    }
//}
