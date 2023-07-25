package com.example.demo.services;

import com.example.demo.models.Amenity;
import com.example.demo.models.RoleInfo;
import com.example.demo.models.RoleInfo;
import com.example.demo.repositories.UserInfoRepo;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmOuterJoinEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
@Service
public class UserInfoService {
    @Autowired
    public UserInfoService(UserInfoRepo userInfoRepo) {
        this.userInfoRepo = userInfoRepo;
    }

    private final UserInfoRepo userInfoRepo;
    public void UpdateInfo(String email, String fname){
            //, String lname, String password, String bloodType, String phoneNo, String city, String emergencyPhoneNo) {
        Optional<RoleInfo> optionalUserInfo = userInfoRepo.findByEmail(email);
        if(optionalUserInfo.isPresent()){
            RoleInfo userInfo = optionalUserInfo.get();
            if(fname!=null &&
            fname.length()>0 &&
            !Objects.equals(fname, userInfo.getFname()))
                userInfo.setFname(fname);
//            if(lname!=null &&
//                    lname.length()>0 &&
//                    !Objects.equals(lname, userInfo.getLname()))
//                userInfo.setLname(lname);
//            if(password!=null &&
//                    password.length()>8 &&
//                    !Objects.equals(password, userInfo.getPassword()))
//                userInfo.setPassword(password);
//            if(bloodType!= null &&
//            bloodType.length()>0 &&
//            !Objects.equals(bloodType, userInfo.getBloodType()) )
//                userInfo.setBloodType(bloodType);
//            if (phoneNo!= null &&
//            phoneNo.length()>4 &&
//            !Objects.equals(phoneNo, userInfo.getPhoneNo())){
//                userInfo.setPhoneNo(phoneNo);
//            }
//            if (city!= null &&
//            city.length()>0 &&
//            !Objects.equals(city, userInfo.getCity()))
//                userInfo.setCity(city);
//            if (emergencyPhoneNo!= null &&
//                    emergencyPhoneNo.length()>4 &&
//                    !Objects.equals(emergencyPhoneNo, userInfo.getEmergencyPhoneNo())) {
//                userInfo.setEmergencyPhoneNo(emergencyPhoneNo);
//            }
            userInfoRepo.save(userInfo);
            }

        else {
            //test this one. it is pre-built
            throw new UsernameNotFoundException(email);
        }
    }
}
