package com.example.demo.repositories;

import com.example.demo.models.Admin_Type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Admin_TypeRepo extends JpaRepository<Admin_Type, Integer> {
  ;
}
