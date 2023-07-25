package com.example.demo.controllers;

import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.exceptions.DiscountNotFoundException;
import com.example.demo.models.Amenity;
import com.example.demo.models.Discount;
import com.example.demo.models.Registered_In_Gym;
import com.example.demo.repositories.AmenityRepo;
import com.example.demo.services.AmenityService;
import com.example.demo.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {
    private DiscountService discountService;


@Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/add-discount")
    public void RegisterNewDiscount(@RequestBody Discount discount)
    {
        discountService.RegisterNewDiscount(discount);
    }
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<List<Discount>> FindDiscountById(@PathVariable Integer id) {
        List<Discount> discounts = discountService.findDiscountById(id);
        return ResponseEntity.ok(discounts);
    }
    @GetMapping("/find-all")
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        List<Discount> discounts = discountService.getAllDiscounts();
        return ResponseEntity.ok(discounts);
    }


    @PutMapping(path ="/update/{id}")
    public void UpdateDiscount(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String coupon_code,
            @RequestParam(required = false) Integer percentage

    ) throws  DiscountNotFoundException {
        discountService.UpdateDiscountDetails(id, coupon_code, percentage);
    }

    @DeleteMapping("/del/{id}")
    public void DeleteDiscountById(@PathVariable("id") Integer id) throws DiscountNotFoundException {
        discountService.DeleteDiscountById(id);
    }
    //add a service where the user applies the discount and it gets added to the subscription total price
    //also each admin is responsible for adding discounts for his/her place of service, so there should be a restriction for admins to only add ones they are authorized to access
}





