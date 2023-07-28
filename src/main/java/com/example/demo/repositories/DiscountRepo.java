package com.example.demo.repositories;

import com.example.demo.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DiscountRepo extends JpaRepository<Discount, Integer> {
//   Optional<Discount> findById(Integer Id);
  Discount findDiscountByCouponCode(String couponCode);
}
