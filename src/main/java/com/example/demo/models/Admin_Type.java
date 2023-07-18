package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Admin_type")
public class Admin_Type {
    @Id
    @GeneratedValue
    private Integer Id;
    private String admin_type;

    @OneToMany(mappedBy = "adminType", cascade = CascadeType.ALL)
    private List<Admin_Role> adminRoles;

}
