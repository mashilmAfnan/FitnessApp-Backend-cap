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
@Table (name = "Session")
public class Session {
    @Id
    @GeneratedValue
    private Integer Id;
    private LocalDate session_date;
    private LocalTime time;

    @ManyToMany
    @JoinTable(name="session_room",
            joinColumns = @JoinColumn(name="session_id"),
            inverseJoinColumns= @JoinColumn(name="room_no"))
    private Set<Room> room = new HashSet<>();


    @ManyToMany
    @JoinTable( name="session-booking",
               joinColumns = @JoinColumn(name="session_id"),
            inverseJoinColumns= @JoinColumn(name="session_booking_id")
    )
    private Set<Room> sessionBooking = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "session_place",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id")
    )
    private Set<PlaceOfService> placesOfService;

    @ManyToMany(mappedBy = "sessions")
    private Set<SessionBooking> bookings;




}
