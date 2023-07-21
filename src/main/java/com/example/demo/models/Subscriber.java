package com.example.demo.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Id
    @GeneratedValue
    private Integer subscriber_Id;
  //  private Integer subscription_Id;
    private Integer pt_id;





    @OneToOne
    @JoinColumn(name = "subscription_id", insertable = false, updatable = false)
    private Subscription subscription;



    @ManyToMany(mappedBy = "subscribers")
    private Set<SessionBooking> sessionBookings = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserRole user;

    @OneToMany(mappedBy = "subscriber")
    private List<Feedback> feedbacks = new ArrayList<>();
//    @ManyToMany
//    @JoinTable(
//            name = "subscriber_feedback",
//            joinColumns = @JoinColumn(name = "subscriber_id"),
//            inverseJoinColumns = @JoinColumn(name = "feedback_id")
//    )
//    private List<Feedback> feedbacks= new ArrayList<>();



}
