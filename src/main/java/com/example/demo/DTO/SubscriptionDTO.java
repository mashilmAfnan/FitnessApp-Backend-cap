package com.example.demo.DTO;

import com.example.demo.models.Discount;
import com.example.demo.models.Package;
import com.example.demo.models.UserRole;
import com.example.demo.services.DiscountService;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDTO implements Serializable {
    private Integer userId;
    private Integer packageId;
    private String couponCode;
    public static SubscriptionDTO mapFromUserRolePackageDiscount(UserRole userRole, Package pack, Discount discount) {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setUserId(userRole.getId());
        subscriptionDTO.setPackageId(pack.getId());
        subscriptionDTO.setCouponCode(discount.getCouponCode());
        return subscriptionDTO;
    }
}
