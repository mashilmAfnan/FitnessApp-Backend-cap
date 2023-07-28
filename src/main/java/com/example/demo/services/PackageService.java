package com.example.demo.services;

import com.example.demo.exceptions.DiscountNotFoundException;
import com.example.demo.exceptions.PackageNotFoundException;
import com.example.demo.models.Discount;
import com.example.demo.models.Package;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.repositories.PackageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PackageService {
    private PackageRepo packageRepo;
    @Autowired
    public PackageService(PackageRepo packageRepo) {
        this.packageRepo = packageRepo;
    }

    public Package FindPackageById(Integer id) {
        return packageRepo.findById(id).orElse(null);
    }
    public List<Package> getAllPackages() {
        return packageRepo.findAll();
    }

    public void RegisterNewPackage(Package pack) {
        packageRepo.save(pack);
    }
    @Transactional
public void UpdatePackage(Integer id, String type, Double price) throws PackageNotFoundException {
        System.out.println("\n\n\n Hello from update package service");
        Optional<Package> optionalPackage = packageRepo.findById(id);
        if (optionalPackage.isPresent()) {
            System.out.println("\n\n\nHello I am present");
            Package pack = optionalPackage.get();
//            if (price!= null &&
//                    price>0){
                System.out.println("\n\n\n Hello from update package price");
                pack.setPrice(price);
//            }
            if (type!= null &&
                    type.length()>0 &&
                    !Objects.equals(type, pack.getType())){
                pack.setType(type);
            }
            packageRepo.save(pack);
        } else {
            throw new PackageNotFoundException(id);
        }
}
    public void DeletePackageById(Integer id) {
        boolean exists = packageRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("book with id " + id + " does not even exist ");
        }
        packageRepo.deleteById(id);
    }


    public Package getPackageById(Integer packageId) {
        return packageRepo.findById(packageId).orElse(null);
    }
}
