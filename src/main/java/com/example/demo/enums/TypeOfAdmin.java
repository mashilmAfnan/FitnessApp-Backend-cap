package com.example.demo.enums;

import com.example.demo.security.Permission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
//@NoArgsConstructor
public enum TypeOfAdmin {
    DIETITIAN,
    GYMADMIN, //Add the amenity controllers
    PERSONAL_TRAINER,
    PHYSIOTHERAPIST;
//    @Getter
//    private final Set<Permission> permissions;



}
//only gym admin and super admin can edit amenities' availability