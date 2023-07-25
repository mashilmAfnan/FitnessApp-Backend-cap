package com.example.demo.repositories;


import com.example.demo.models.PlaceOfService;
import com.example.demo.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Integer> {
    Optional<Subscription> findSubscriptionById(Integer Id);
}
