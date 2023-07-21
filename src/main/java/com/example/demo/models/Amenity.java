package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
//import javax.persistence.Id;
//import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "amenity")
public class Amenity {
    @Id
    @GeneratedValue

    private Integer id;
    private String type;
    private Boolean availability;
    @ManyToMany
    private Set<PlaceOfService> place = new HashSet<>();

}