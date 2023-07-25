package com.example.demo.controllers;

import com.example.demo.exceptions.PackageNotFoundException;
import com.example.demo.models.Package;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/package")
public class PackageController {


    private PackageService packageService;
    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<List<Package>> FindDiscountById(@PathVariable Integer id) {
        List<Package> packages = packageService.FindPackageById(id);
        return ResponseEntity.ok(packages);
    }
    @GetMapping("/find-all")
    public ResponseEntity<List<Package>> getAllPackages() {
        List<Package> registeredInGyms = packageService.getAllPackages();
        return ResponseEntity.ok(registeredInGyms);
    }
    @PostMapping("/add-package")
    public void RegisterNewPackage(@RequestBody Package pack)
    {
        packageService.RegisterNewPackage(pack);
    }


    @PutMapping(path ="/update/{id}")
    public void UpdatePackage(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double price
            ) throws PackageNotFoundException {
        System.out.println("\n\n\n Hello from update package controller");
        packageService.UpdatePackage(id, type, price);
    }

    @DeleteMapping("/del/{id}")
    public void DeletePackageById(@PathVariable("id") Integer id)
    {
        packageService.DeletePackageById(id);
    }
}


//ADD A SERVICE TO ALLOW THE USER TO PURCHASE A PACKAGE AND APPLY DISCOUNT IF POSSIBLE


