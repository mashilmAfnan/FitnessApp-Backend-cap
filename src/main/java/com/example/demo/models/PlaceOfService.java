package com.example.demo.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PlaceOfService")
public class PlaceOfService {
   @Id
   @GeneratedValue
    private Integer id;
    private String name;
    private String type;
    private String location;


    @ManyToMany
    @JoinTable(name="place_amenity",
            joinColumns = @JoinColumn(name="place_id"),
            inverseJoinColumns= @JoinColumn(name="amenity_id")    )
    private Set<Amenity> amenity = new HashSet<>();


    @ManyToMany
    @JoinTable(name="place_room",
    joinColumns = @JoinColumn(name="place_id"),
    inverseJoinColumns= @JoinColumn(name="room_no")    )
    private Set<Room> room = new HashSet<>();

 @ManyToMany(mappedBy = "placesOfService")
 private List<Admin_Role> adminRoles= new ArrayList<>();

 @ManyToMany(mappedBy = "placesOfService")
 private List<Registered_In_Gym> registeredInGyms= new ArrayList<>();

 @ManyToMany(mappedBy = "placesOfService")
 private List<Session> sessions= new ArrayList<>();

 @OneToMany(mappedBy = "placeOfService")
 private List<Package> packages = new ArrayList<>();

 @OneToMany(mappedBy = "placeOfService")
 private List<Discount> discounts = new ArrayList<>();

}
