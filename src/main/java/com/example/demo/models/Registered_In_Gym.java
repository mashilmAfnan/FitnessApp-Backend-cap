package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Registered_In_Gym")   //check if we are getting a different admin id
public class Registered_In_Gym {
    //both admin_id and place_id are fks
    @Id
    @GeneratedValue
    private Integer admin_id;

    private  Integer place_id;
    @ManyToMany
    @JoinTable(
            name = "gym_placeofservice",
            joinColumns = @JoinColumn(name = "registered_in_gym_id"),
            inverseJoinColumns = @JoinColumn(name = "place_of_service_id")
    )
    private List<PlaceOfService> placesOfService= new ArrayList<>();
}
