package com.example.demo.DTO;

import com.example.demo.models.Session;
import com.example.demo.models.Subscriber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionBookingDTO implements Serializable {
    private  Integer subscriberId;
    private  Integer sessionId;

    public static SessionBookingDTO mapFromSubscriberSession(Session session, Subscriber subscriber)
    {
        SessionBookingDTO sessionBookingDTO = new SessionBookingDTO();
        sessionBookingDTO.setSessionId(session.getId());
        sessionBookingDTO.setSubscriberId(subscriber.getSubscriberId());
        return sessionBookingDTO;
    }
}
