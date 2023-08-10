package com.example.demo.enums;

import lombok.RequiredArgsConstructor;

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