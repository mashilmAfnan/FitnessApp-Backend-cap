package com.example.demo.security;

import com.example.demo.enums.RoleMain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String fname;
    private String lname;
    private String email;
    private  String password;
    private String bloodType;
    private String phoneNo;
    private String city;
    private String emergencyPhoneNo;
    private RoleMain role;
}
