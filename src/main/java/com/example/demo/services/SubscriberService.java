package com.example.demo.services;

import com.example.demo.models.Subscriber;
import com.example.demo.repositories.SubscriberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Character.getType;

@Service
public class SubscriberService {
    private static SubscriberRepo subscriberRepo;
    @Autowired
    public SubscriberService(SubscriberRepo subscriberRepo) {
        this.subscriberRepo = subscriberRepo;
    }

    public static void AddSubscriberId(Integer userId, Integer subscriptionId) {
        Subscriber subscriber = new Subscriber();
        subscriber.setSubscriberId(userId);
        subscriber.setSusbcriptionId(subscriptionId);
        subscriberRepo.save(subscriber);
    }


    //does this need to be a list or optional or a simple Sub will do as return type?
    public List<Subscriber> findSubscriberById(Integer id) {
        return subscriberRepo.findById(id).stream().collect(Collectors.toList());
    }
    public List<Subscriber> getAllSubscribers() {
        return subscriberRepo.findAll();
    }


    public void RegisterNewSubscriber(Subscriber subscriber) {   subscriberRepo.save(subscriber);    }


    public void deleteSubscriberById(Integer id) {

        boolean exists = subscriberRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("subscriber with id " + id + " does not even exist ");
        }
        subscriberRepo.deleteById(id);
    }
//does it need a controller??
    public Integer findSubscriberId(Subscriber subscriber) {
      return  subscriber.getSubscriberId();
    }

    public Subscriber getSubscriberById(Integer subscriberId) {
        return subscriberRepo.findById(subscriberId).orElse(null);
    }
}
