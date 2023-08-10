package com.example.demo.models;

import com.example.demo.enums.RoleMain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_info")
public class RoleInfo implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;

    private String fname;
    private String lname;
    private String email;
    private String password;
    private String bloodType;
    private String phoneNo;
    private String city;
    private String emergencyPhoneNo;

    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private Admin_Role admin;

    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private UserRole user;


    @Enumerated(EnumType.STRING)
    private RoleMain role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
