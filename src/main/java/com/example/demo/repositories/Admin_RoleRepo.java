package com.example.demo.repositories;

import com.example.demo.models.Admin_Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Admin_RoleRepo extends JpaRepository<Admin_Role, Integer> {

}
//clean repo to only keep the options we need and dont have by default