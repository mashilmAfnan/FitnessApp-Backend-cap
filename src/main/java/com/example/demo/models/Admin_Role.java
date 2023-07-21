package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Admin_Role")
public class Admin_Role {
@Id
@Column(name = "user_info_id")
private Integer id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_id")
    private RoleInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "admin_type_id") // Specify the correct foreign key column name
    private Admin_Type adminType;

    @ManyToMany
    @JoinTable(
            name = "admin_role_placeofservice",
            joinColumns = @JoinColumn(name = "admin_role_id"),
            inverseJoinColumns = @JoinColumn(name = "placeofservice_id")
    )
    private List<PlaceOfService> placesOfService = new ArrayList<>();


}
