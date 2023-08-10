package com.example.demo.repositories;
import com.example.demo.models.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PackageRepo extends JpaRepository<Package, Integer> {

}
