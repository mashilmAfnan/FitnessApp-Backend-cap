package com.example.demo.models;

import javax.persistence.*;
import java.util.List;

public class Admin_Role {
@Id
private Integer user_info_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_id")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "admin_type_id") // Specify the correct foreign key column name
    private Admin_Type adminType;

    @ManyToMany
    @JoinTable(
            name = "admin_role_placeofservice",
            joinColumns = @JoinColumn(name = "admin_role_id"),
            inverseJoinColumns = @JoinColumn(name = "placeofservice_id")
    )
    private List<PlaceOfService> placesOfService;


}
