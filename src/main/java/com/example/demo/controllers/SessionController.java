package com.example.demo.controllers;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.exceptions.SessionNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.models.Session;
import com.example.demo.services.AmenityService;
import com.example.demo.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/session")
public class SessionController {
    private SessionService sessionService;
@Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<Session>> findSessionById(@PathVariable Integer id) {
        List<Session> sessions = sessionService.findSessionById(id);
        return ResponseEntity.ok(sessions);
    }
    @GetMapping("/find-all")
    public ResponseEntity<List<Session>> getAllSessions() {
        List<Session> registeredInGyms = sessionService.getAllSessions();
        return ResponseEntity.ok(registeredInGyms);
    }
    @PostMapping("/add-session")
    public void RegisterNewSession(@RequestBody Session session)
    {
        sessionService.RegisterNewSession(session);
    }
    @PutMapping(path ="/update/{id}")
    public void updateSession(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false)LocalDate date,
            @RequestParam(required = false)LocalTime time
            ) throws SessionNotFoundException {
        sessionService.updateSession(id, date, time);
    }
    @DeleteMapping("/del/{id}")
    public void DeleteSessionById(@PathVariable("id") Integer id)
    {
        sessionService.deleteSessionById(id);
    }
}





