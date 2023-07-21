package com.example.demo.services;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.repositories.AmenityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;
@Service
public class AmenityService {
    private AmenityRepo amenityRepo;
@Autowired
    public AmenityService(AmenityRepo amenityRepo) {
        this.amenityRepo = amenityRepo;
    }

    public List<Amenity> findAmenityByName(String name) {
        return amenityRepo.findAmenityByAmenityType(name);
    }

    public void RegisterNewAmenity(Amenity amenity) {
        Amenity newAmenity = new Amenity();
        amenityRepo.save(newAmenity);
    }
  @Transactional
    public void updateAmenityAvailability(Integer id, Boolean availability) throws AmenityNotFoundException {
        Optional<Amenity> optionalAmenity = amenityRepo.findById(id);

        if (optionalAmenity.isPresent()) {
            Amenity amenity = optionalAmenity.get();
            amenity.setAvailability(availability);
            amenityRepo.save(amenity);
        } else {

            throw new AmenityNotFoundException(id);
        }
    }

    public void deleteAmenityById(Integer id) {

        boolean exists = amenityRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("book with id " + id + " does not even exist ");
        }
        amenityRepo.deleteById(id);
    }
}
