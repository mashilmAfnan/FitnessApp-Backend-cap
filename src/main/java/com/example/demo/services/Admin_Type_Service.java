package com.example.demo.services;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.models.Admin_Type;
import com.example.demo.models.Amenity;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.models.TypeOfAdmin;
import com.example.demo.repositories.Admin_TypeRepo;
import com.example.demo.repositories.AmenityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

import static java.lang.Character.getType;

@Service
public class Admin_Type_Service {
    private Admin_TypeRepo adminTypeRepo;
    @Autowired
    public Admin_Type_Service(Admin_TypeRepo adminTypeRepo) {
        this.adminTypeRepo = adminTypeRepo;
    }




    public List<Admin_Type> getAllAdminTypes() {
        return adminTypeRepo.findAll();
    }

    public void RegisterNewAdminType(Admin_Type adminType) {


        adminTypeRepo.save(adminType);
    }

// OR should it be by type_of_admin
    public void deleteAdminType(Integer id) {
 //delete the enum?
        boolean exists = adminTypeRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Admin type with name " + id + " does not even exist ");
        }
        adminTypeRepo.deleteById(id);
    }
}
