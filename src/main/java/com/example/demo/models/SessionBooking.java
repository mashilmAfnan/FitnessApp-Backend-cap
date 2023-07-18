package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private Integer Id;
    private LocalDate session_date;
    private LocalTime session_time;
    @ManyToMany
    @JoinTable(
            name = "session_booking_session",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id")
    )
    private Set<Session> sessions;

    @ManyToMany(mappedBy = "sessionBookings")
    private List<Subscriber> subscribers;


}
