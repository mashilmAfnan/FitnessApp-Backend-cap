package com.example.demo.repositories;


import com.example.demo.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

}
