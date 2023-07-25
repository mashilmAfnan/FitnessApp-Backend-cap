package com.example.demo.services;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.models.Subscriber;
import com.example.demo.repositories.AmenityRepo;
import com.example.demo.repositories.SubscriberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Character.getType;

@Service
public class SubscriberService {
    private SubscriberRepo subscriberRepo;
    @Autowired
    public SubscriberService(SubscriberRepo subscriberRepo) {
        this.subscriberRepo = subscriberRepo;
    }


//does this need to be a list or optional or a simple Sub will do as return type?
    public List<Subscriber> findSubscriberById(Integer id) {
        return subscriberRepo.findSubscriberBySubscriberId(id).stream().collect(Collectors.toList());
    }
    public List<Subscriber> getAllSubscribers() {
        return subscriberRepo.findAll();
    }


    public void RegisterNewSubscriber(Subscriber subscriber) {

        //System.out.println("In service\n" + amenity + "\n");

        subscriberRepo.save(subscriber);
    }


    public void deleteSubscriberById(Integer id) {

        boolean exists = subscriberRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("subscriber with id " + id + " does not even exist ");
        }
        subscriberRepo.deleteById(id);
    }
}
