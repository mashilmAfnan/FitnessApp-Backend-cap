package com.example.demo.repositories;


import com.example.demo.models.PlaceOfService;
import com.example.demo.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {
    Optional<UserRole> findById(Integer Id);
}
