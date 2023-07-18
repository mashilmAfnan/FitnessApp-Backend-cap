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
    private Integer RoomNo;
    private String purpose;
    private Integer capacity;
    private  Boolean availability;
    @ManyToMany
    private Set<PlaceOfService> place = new HashSet<>();

    @ManyToMany(mappedBy = "rooms")
    private Set<Session> sessions;
}
