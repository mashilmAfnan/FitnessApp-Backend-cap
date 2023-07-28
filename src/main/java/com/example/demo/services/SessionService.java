package com.example.demo.services;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.exceptions.SessionNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.models.Session;
import com.example.demo.repositories.AmenityRepo;
import com.example.demo.repositories.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class SessionService {
    private SessionRepo sessionRepo;



    @Autowired
    public SessionService(SessionRepo sessionRepo) {
        this.sessionRepo = sessionRepo;
    }

    public List<Session> findSessionById(Integer id) {
        return sessionRepo.findById(id).stream().toList();
    }

    public void RegisterNewSession(Session session) {

        sessionRepo.save(session);
    }
    public List<Session> getAllSessions() {
        return sessionRepo.findAll();
    }

    @Transactional
    public void updateSession(Integer id, LocalDate date, LocalTime time) throws SessionNotFoundException {
        Optional<Session> optionalSession = sessionRepo.findById(id);

        if (optionalSession.isPresent()) {
            Session session = optionalSession.get();
            if (date!= null &&
                    !Objects.equals(date, session.getSession_date()))
                session.setSession_date(date);
            if (time!= null &&
                    !Objects.equals(time, session.getSession_time()))
                session.setSession_time(time);
         //   amenity.setAvailability(availability);
            sessionRepo.save(session);
        } else {

               throw new SessionNotFoundException(id);
        }
    }

    public void deleteSessionById(Integer id) {

        boolean exists = sessionRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("session with id " + id + " does not even exist ");
        }
        sessionRepo.deleteById(id);
    }

    public Integer findSessionId(Session session) {
        return session.getId();
    }

    public Session getSessionById(Integer sessionId) {
      return sessionRepo.findById(sessionId).orElse(null);


    }
}
