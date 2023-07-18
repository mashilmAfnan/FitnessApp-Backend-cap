package com.example.demo.repositories;

import com.example.demo.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {

    Optional<UserInfo> findByEmail(String email);
}
