package com.example.demo.services;

import com.example.demo.DTO.SubscriptionDTO;
import com.example.demo.exceptions.AmenityNotFoundException;
import com.example.demo.models.*;
import com.example.demo.models.Package;
import com.example.demo.repositories.AmenityRepo;
import com.example.demo.repositories.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Character.getType;

@Service
public class SubscriptionService {
    private final SubscriptionRepo subscriptionRepo;
    private  final SubscriberService subscriberService;
    private final UserInfoService userInfoService;
    private final PackageService packageService;
    private final DiscountService discountService;
    @Autowired
    public SubscriptionService(SubscriptionRepo subscriptionRepo, SubscriberService subscriberService, UserInfoService userInfoService, PackageService packageService, DiscountService discountService) {
        this.subscriptionRepo = subscriptionRepo;
        this.subscriberService = subscriberService;
        this.userInfoService = userInfoService;
        this.packageService = packageService;
        this.discountService = discountService;
    }



//    public List<Subscription> findSubscriptionById(Integer id) {
//        return subscriptionRepo.findSubscriptionById(id).stream().collect(Collectors.toList());
//    }


//register what about gettign all subscriptions??
public void addSubscription(SubscriptionDTO subscriptionDTO) {
    Integer userId = subscriptionDTO.getUserId();
    Integer packageId = subscriptionDTO.getPackageId();
    String couponCode = subscriptionDTO.getCouponCode();
    if (userId!= null && packageId!= null) {
        UserRole user = userInfoService.getUserById(userId);
        Package pack = packageService.getPackageById(packageId);
        Subscription subscription = new Subscription();
        subscription.setPackageEntity(pack);
        if (couponCode!= null){
            Discount discount = discountService.getDiscountByCouponCode(couponCode);
            subscription.setDiscount(discount);
        } else {
            subscription.setDiscount(null);
        }  subscriptionRepo.save(subscription);
              SubscriberService.AddSubscriberId(userId, subscription.getId()); //save the user to the subscriber table.
    }}

//    public void deleteSubscriptionById(Integer id) {
//
//        boolean exists = subscriptionRepo.existsById(id);
//        if (!exists) {
//            throw new IllegalStateException("book with id " + id + " does not even exist ");
//        }
//        subscriptionRepo.deleteById(id);
//    }
}
