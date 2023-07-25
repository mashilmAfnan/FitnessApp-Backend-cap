package com.example.demo.services;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.exceptions.DiscountNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Discount;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.repositories.AmenityRepo;
import com.example.demo.repositories.DiscountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscountService {
    private DiscountRepo discountRepo;
    @Autowired
    public DiscountService(AmenityRepo amenityRepo) {
        this.discountRepo = discountRepo;
    }

    public void RegisterNewDiscount(Discount discount) {

        discountRepo.save(discount);
    }
    public List<Discount> getAllDiscounts() {
        return discountRepo.findAll();
    }

    public List<Discount> findDiscountById(Integer id) {

        return discountRepo.findById(id)
                .stream()
                .collect(Collectors.toList());
    }


   @Transactional
public void UpdateDiscountDetails(Integer id, String couponCode, Integer percentage) throws DiscountNotFoundException {
       Optional <Discount> optionalDiscount = discountRepo.findById(id);
          //.orElseThrow(()-> new IllegalStateException("DISCOUNT WITH THIS ID DOES NOT EXIST"));
        if (optionalDiscount.isPresent()) {
            Discount discount = optionalDiscount.get();
            if (couponCode!= null &&
            couponCode.length()>0 &&
             couponCode.length() <=5 &&
            !Objects.equals(couponCode, discount.getCouponCode())){
                discount.setCouponCode(couponCode);
            }
            if (percentage!= null &&
               percentage>0){
                discount.setPercentage(percentage);
            }
            discountRepo.save(discount);
        } else {

            throw new DiscountNotFoundException(id);
        }
  }

    public void DeleteDiscountById(Integer id) throws DiscountNotFoundException {

        boolean exists = discountRepo.existsById(id);
        if (!exists) {
            throw new DiscountNotFoundException(id);
        }
        discountRepo.deleteById(id);
    }



}
