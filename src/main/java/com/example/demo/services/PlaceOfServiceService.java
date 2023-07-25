package com.example.demo.services;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.exceptions.PlaceNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.PlaceOfService;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.repositories.AmenityRepo;
import com.example.demo.repositories.PlaceOfServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class PlaceOfServiceService {


    private PlaceOfServiceRepo placeOfServiceRepo;
    @Autowired
    public PlaceOfServiceService(PlaceOfServiceRepo placeOfServiceRepo) {
        this.placeOfServiceRepo = placeOfServiceRepo;
    }
//WHAT SHOULD THE LIST HAVE AS RETURN VALUE? IT WAS EMPTY WHEN I TESTED IT
    public List<PlaceOfService> findPlaceOfServiceByName(String name) {
        return placeOfServiceRepo.findByName(name).stream().toList();
    }
    public List<PlaceOfService> getAllPlaces() {
        return placeOfServiceRepo.findAll();
    }

    public void RegisterNewPlace(PlaceOfService placeOfService) {

        placeOfServiceRepo.save(placeOfService);
    }
    //update plac edata insert logic
    @Transactional
    public void UpdatePlaceData(Integer id,String name, String location) throws  PlaceNotFoundException {
        Optional<PlaceOfService> optionalPlace = placeOfServiceRepo.findById(id);
        if (optionalPlace.isPresent()) {
            PlaceOfService placeOfService = optionalPlace.get();
            if (name!= null &&
            name.length()>0 &&
            !Objects.equals(name, placeOfService.getName()))
                placeOfService.setName(name);
            if (location!= null &&
                    location.length()>0 &&
                    !Objects.equals(location, placeOfService.getLocation()))
                placeOfService.setLocation(location);
            placeOfServiceRepo.save(placeOfService);
        } else {

            throw new PlaceNotFoundException(id);
        }
    }

    public void deletePlaceById(Integer id) {

        boolean exists = placeOfServiceRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Place with id " + id + " does not even exist ");
        }
        placeOfServiceRepo.deleteById(id);
    }
}
