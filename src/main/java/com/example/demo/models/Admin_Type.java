package com.example.demo.models;

import com.example.demo.enums.TypeOfAdmin;
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
@Table(name = "Admin_type")
public class Admin_Type {
    @Id
    @GeneratedValue
    private Integer id;

    private TypeOfAdmin admin_type;

    @OneToMany(mappedBy = "adminType", cascade = CascadeType.ALL)
    private List<Admin_Role> adminRoles= new ArrayList<>();

}
