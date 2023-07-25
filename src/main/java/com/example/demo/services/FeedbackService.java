package com.example.demo.services;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Feedback;
import com.example.demo.repositories.AmenityRepo;
import com.example.demo.repositories.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;
@Service
public class FeedbackService {
    private FeedbackRepo feedbackRepo;



    @Autowired

    public FeedbackService(FeedbackRepo feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepo.findAll().stream().toList();
    }
//only a subscriber should be allowed to write a feedback
    public void RegisterNewFeedback(Feedback feedback) {

        feedbackRepo.save(feedback);
    }


    public void DeleteFeedbackById(Integer id) {

        boolean exists = feedbackRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Feedback with id " + id + " does not even exist ");
        }
        feedbackRepo.deleteById(id);
        //printing deleted successfully messages
    }


}
