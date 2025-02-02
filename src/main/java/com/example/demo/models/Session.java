package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Session")
public class Session {
    @Id
    @GeneratedValue
    @Column(name = "session_id")
    private Integer id;

    private String name;
    private LocalDate session_date;
    private LocalTime session_time;
    //add price of session
//    private Double session_price;

//1.	Add findAll() to services
    @ManyToMany
    @JoinTable(name = "session_room",
               joinColumns = @JoinColumn(name = "session_id"),
               inverseJoinColumns = @JoinColumn(name = "room_no"))
               private Set<Room> room = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "sessionbookingid-sessionid",
               joinColumns = @JoinColumn(name = "session_id"),
               inverseJoinColumns = @JoinColumn(name = "session_booking_id")
    )
               private Set<SessionBooking> SessionBooking = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "session_place",
               joinColumns = @JoinColumn(name = "session_id"),
               inverseJoinColumns = @JoinColumn(name = "place_id")
    )
               private Set<PlaceOfService> placesOfService = new HashSet<>();

    @ManyToMany(mappedBy = "sessions")
               private Set<Room> rooms = new HashSet<>();


}
