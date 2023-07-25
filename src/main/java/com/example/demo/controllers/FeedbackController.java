package com.example.demo.controllers;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Feedback;
import com.example.demo.services.AmenityService;
import com.example.demo.services.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {
    private FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/add-feedback")
    public void RegisterNewFeedback(@RequestBody Feedback feedback)
    {
        feedbackService.RegisterNewFeedback(feedback);
    }
    @GetMapping("get-feedbacks")
    public ResponseEntity<List<Feedback>> GetAllFeedbacks(@PathVariable String name) {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks().stream().toList();
        return ResponseEntity.ok(feedbacks);
    }


    //delete a feedback if it is reported
    @DeleteMapping("/del/{id}")
    public void deleteFeedbackById(@PathVariable("id") Integer id)
    {
        feedbackService.DeleteFeedbackById(id);
    }

    //how to display feedbacks on the website? leave for frontend
}








