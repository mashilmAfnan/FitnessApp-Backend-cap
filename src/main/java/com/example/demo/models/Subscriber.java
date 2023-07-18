package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Subscriber")
public class Subscriber {
    //subsc id, subription id , pt id
    private Integer subscriber_Id;
    private Integer subscription_Id;
    private Integer pt_id;
    @ManyToMany
    @JoinTable(name="subscriber_feedback",
            joinColumns = @JoinColumn(name="subscriber_id"),
            inverseJoinColumns= @JoinColumn(name="feedback_id")
    )
    private Set<Feedback> feedback = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "subscriber_session_booking",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "session_booking_id")
    )
    private List<SessionBooking> sessionBookings;

    @OneToOne(mappedBy = "subscriber")
    private Subscription subscription;

    @ManyToMany
    @JoinTable(
            name = "subscriber_user",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Userr> users;

    @ManyToMany
    @JoinTable(
            name = "subscriber_feedback",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "feedback_id")
    )
    private List<Feedback> feedbacks;

}
