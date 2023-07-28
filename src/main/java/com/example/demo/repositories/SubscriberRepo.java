package com.example.demo.repositories;


import com.example.demo.models.Amenity;
import com.example.demo.models.PlaceOfService;
import com.example.demo.models.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SubscriberRepo extends JpaRepository<Subscriber, Integer> {
//    Optional<Subscriber> findSubscriberBySubscriberId(Integer Id);


}
