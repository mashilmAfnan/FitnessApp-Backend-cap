package com.example.demo.services;

import com.example.demo.DTO.SessionBookingDTO;
import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.exceptions.SessionCannotBeBookedException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Session;
import com.example.demo.models.SessionBooking;
import com.example.demo.models.Subscriber;
import com.example.demo.repositories.AmenityRepo;
import com.example.demo.repositories.SessionBookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SessionBookingService {
    private final SessionBookingRepo sessionBookingRepo;
    private final SubscriberService subscriberService;
    private final SessionService sessionService;
    @Autowired
    public SessionBookingService(SessionBookingRepo sessionBookingRepo, SubscriberService subscriberService, SessionService sessionService) {
        this.sessionBookingRepo = sessionBookingRepo;
        this.subscriberService = subscriberService;
        this.sessionService = sessionService;
    }
    public void subscriberBookSession(SessionBookingDTO sessionBookingDTO) throws SessionCannotBeBookedException {
  Integer sessionId = sessionBookingDTO.getSessionId();
  Integer subscriberId = sessionBookingDTO.getSubscriberId();
        if (subscriberId != null && sessionId != null) {
           Subscriber subscriber = subscriberService.getSubscriberById(subscriberId);
           Session session = sessionService.getSessionById(sessionId);
            SessionBooking sessionBooking = new SessionBooking();
            sessionBooking.setSubscriber(subscriber);
            sessionBooking.setSession(session);
            sessionBookingRepo.save(sessionBooking);
        } else {
            throw new SessionCannotBeBookedException();
        }

    }

//    public List<SessionBooking> findAmenityByName(String name) {
//        return sessionBookingRepo.findAmenityByAmenityType(name);
//    }

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
}
