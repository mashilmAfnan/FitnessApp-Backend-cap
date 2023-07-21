package com.example.demo.repositories;

import com.example.demo.models.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface AmenityRepo extends JpaRepository<Amenity, Integer> {
    @Query("SELECT a FROM Amenity a WHERE a.type = :type")
    List<Amenity> findAmenityByAmenityType(String type);

}
