package com.example.demo.repositories;

import com.example.demo.models.RoleInfo;
import com.example.demo.models.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserInfoRepo extends JpaRepository<RoleInfo, Integer> {
//rename role info repo
//    @Query()
    Optional<RoleInfo> findByEmail(String email);

   Boolean existsByEmail(String email);
}
