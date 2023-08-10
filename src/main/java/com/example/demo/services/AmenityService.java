package com.example.demo.services;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.repositories.AmenityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

import static java.lang.Character.getType;

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
    public List<Amenity> getAllAmenities() {
        return amenityRepo.findAll();
    }
    public void RegisterNewAmenity(Amenity amenity) {

        System.out.println("In service\n" + amenity + "\n");
        amenityRepo.save(amenity);
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
        }}
//    public static String getType(Object obj) {
//        if (obj instanceof Integer) {
//            return "\n\n\n int";
//        } else if (obj instanceof Double) {
//            return "\n\n\ndouble";
//        } else if (obj instanceof Boolean) {
//            return "\n\n\nboolean";
//        } else {
//            return "\n\n\nunknown";
//        }
//    }
    public void deleteAmenityById(Integer id) {
        boolean exists = amenityRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("book with id " + id + " does not even exist ");
        }
        amenityRepo.deleteById(id);
    }
}
