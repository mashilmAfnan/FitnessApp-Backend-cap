package com.example.demo.services;

import com.example.demo.models.Admin_Type;
import com.example.demo.repositories.Admin_TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void RegisterNewAdminType(Admin_Type adminType) {       adminTypeRepo.save(adminType);   }
    public void deleteAdminType(Integer id) {
        boolean exists = adminTypeRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Admin type with name " + id + " does not even exist ");
        }
        adminTypeRepo.deleteById(id);
    }
}
