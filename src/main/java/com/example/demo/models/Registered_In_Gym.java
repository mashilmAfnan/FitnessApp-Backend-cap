package com.example.demo.models;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Registered_In_Gym {
    //both are fks
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
