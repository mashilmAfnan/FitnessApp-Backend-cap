package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "Room")
public class Room {
    @Id
    @GeneratedValue
    @Column(name = "room_no")
    private Integer roomNo;

    private String purpose;
    private Integer capacity;
    private  Boolean availability;

    @ManyToMany
    @JoinTable(
            name = "room_session",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id")
    )
    private Set<Session> sessions = new HashSet<>();

}
