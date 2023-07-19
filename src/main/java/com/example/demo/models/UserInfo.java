package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
@Entity
@Table (name = "user_info")
public class UserInfo implements UserDetails {


    @Id
    @GeneratedValue
    private Integer Id;
   private String fname;
   private String lname;
   private String email;
   private String password;
   private String BloodType;
   private String phoneNo;
   private String city;
   private String emergencyPhoneNo;
    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private Admin_Role admin;
    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private Userr user;
  //  but it should start from 1 not 0
@Enumerated(EnumType.ORDINAL)
private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
 @Override
  public String getPassword(){
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
