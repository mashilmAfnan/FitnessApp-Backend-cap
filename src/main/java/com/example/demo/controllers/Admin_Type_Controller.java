package com.example.demo.controllers;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.models.Admin_Type;
import com.example.demo.models.Amenity;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.models.TypeOfAdmin;
import com.example.demo.services.Admin_Type_Service;
import com.example.demo.services.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin-type")
public class Admin_Type_Controller {
    @Autowired
    public Admin_Type_Controller(Admin_Type_Service adminTypeService) {
        this.adminTypeService = adminTypeService;
    }

    private Admin_Type_Service adminTypeService;

    @GetMapping("/find-all")
    public ResponseEntity<List<Admin_Type>> getAllAdminTypes() {
        List<Admin_Type> adminTypes = adminTypeService.getAllAdminTypes();
        return ResponseEntity.ok(adminTypes);
    }
    //add find all and give the user access
    @PostMapping("/add-admin-type")
    public void RegisterNewAdminType(@RequestBody Admin_Type adminType)
    {

        adminTypeService.RegisterNewAdminType(adminType);
    }


    @DeleteMapping("/del/{id}")
    public void deleteAdminType(@PathVariable("id")  Integer id)
    {
        adminTypeService.deleteAdminType(id);
    }
}





