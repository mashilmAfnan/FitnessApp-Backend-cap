package com.example.demo.services;

import com.example.demo.models.Amenity;
import com.example.demo.models.RoleInfo;
import com.example.demo.models.RoleInfo;
import com.example.demo.models.UserRole;
import com.example.demo.repositories.UserInfoRepo;
import com.example.demo.repositories.UserRoleRepo;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmOuterJoinEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
@Service
public class UserInfoService {
    private final UserInfoRepo userInfoRepo;
    private final UserRoleRepo userRoleRepo;
    @Autowired
    public UserInfoService(UserInfoRepo userInfoRepo, UserRoleRepo userRoleRepo) {
        this.userInfoRepo = userInfoRepo;
        this.userRoleRepo = userRoleRepo;
    }

    public void UpdateRoleInfo(String email, String fname,
                           String lname, String password, String bloodType, String phoneNo, String city, String emergencyPhoneNo) {
        Optional<RoleInfo> optionalUserInfo = userInfoRepo.findByEmail(email);

        if(optionalUserInfo.isPresent()){

            RoleInfo userInfo = optionalUserInfo.get();
            if(fname!=null &&
            fname.length()>0 &&
            !Objects.equals(fname, userInfo.getFname()))
                userInfo.setFname(fname);
            if(lname!=null &&
                    lname.length()>0 &&
                    !Objects.equals(lname, userInfo.getLname()))
                userInfo.setLname(lname);
            if(password!=null &&
                    password.length()>8 &&
                    !Objects.equals(password, userInfo.getPassword()))
                userInfo.setPassword(password);
            if(bloodType!= null &&
            bloodType.length()>=0 &&
            !Objects.equals(bloodType, userInfo.getBloodType()) ) {

                userInfo.setBloodType(bloodType);
            }
            if (phoneNo!= null &&
            phoneNo.length()>4 &&
            !Objects.equals(phoneNo, userInfo.getPhoneNo())){
                userInfo.setPhoneNo(phoneNo);
            }
            if (city!= null &&
            city.length()>0 &&
            !Objects.equals(city, userInfo.getCity()))
                userInfo.setCity(city);
            if (emergencyPhoneNo!= null &&
                    emergencyPhoneNo.length()>4 &&
                    !Objects.equals(emergencyPhoneNo, userInfo.getEmergencyPhoneNo())) {
                userInfo.setEmergencyPhoneNo(emergencyPhoneNo);
            }

            userInfoRepo.save(userInfo);
            }

        else {
            //test this one. it is pre-built

            throw new UsernameNotFoundException(email);
        }
    }


    public UserRole getUserById(Integer userId) {
        return userRoleRepo.findById(userId).orElse(null);
    }
}
