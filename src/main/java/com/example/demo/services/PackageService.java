package com.example.demo.services;

import com.example.demo.exceptions.DiscountNotFoundException;
import com.example.demo.exceptions.PackageNotFoundException;
import com.example.demo.models.Discount;
import com.example.demo.models.Package;
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

    public List<Package> FindPackageById(Integer id) {
        return packageRepo.findPackageById(id)
                            .stream()
                            .collect(Collectors.toList());
    }

    public void RegisterNewPackage(Package pack) {
        Package newPackage = new Package();
        packageRepo.save(newPackage);
    }
    @Transactional
public void UpdatePackage(Integer id, String type, Double price) throws PackageNotFoundException {

        Optional<Package> optionalPackage = packageRepo.findById(id);
        if (optionalPackage.isPresent()) {
            Package pack = optionalPackage.get();
            if (type!= null &&
                    type.length()>0 &&
                    !Objects.equals(type, pack.getType())){
                pack.setType(type);
            }
            if (price!= null &&
                    price>0){
                pack.setPrice(price);
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


}
