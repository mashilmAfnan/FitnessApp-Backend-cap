package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Session_Booking")
public class SessionBooking {
    @Id
    @GeneratedValue
    @Column(name="session_booking_id")
    private Integer id;
    private LocalDate session_date;
    private LocalTime session_time;
    @ManyToMany
    @JoinTable(
            name = "session_booking_session",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id")    )
    private Set<Session> sessions= new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "subscriber_session_booking",
            joinColumns = @JoinColumn(name = "session_booking_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    private Set<Subscriber> subscribers = new HashSet<>();


}
