package com.example.demo.services;

import com.example.demo.DTO.UserProfileDataDTO;
import com.example.demo.constants;
import com.example.demo.enums.RoleMain;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.RoleInfo;
import com.example.demo.models.UserRole;
import com.example.demo.repositories.UserInfoRepo;
import com.example.demo.repositories.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class UserInfoService {
    private final UserInfoRepo userInfoRepo;
    private final UserRoleRepo userRoleRepo;
    private final UserProfileDataDTO userProfileDataDTO;

    @Autowired
    public UserInfoService(UserInfoRepo userInfoRepo, UserRoleRepo userRoleRepo, UserProfileDataDTO userProfileDataDTO) {
        this.userInfoRepo = userInfoRepo;
        this.userRoleRepo = userRoleRepo;

        this.userProfileDataDTO = userProfileDataDTO;
    }
    public void UpdateRoleInfoByEmail(String email, String fname, String lname, String password, String bloodType, String phoneNo, String city, String emergencyPhoneNo) {
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
            } if (phoneNo!= null &&
            phoneNo.length()>4 &&
            !Objects.equals(phoneNo, userInfo.getPhoneNo())){
                userInfo.setPhoneNo(phoneNo);
            } if (city!= null &&
            city.length()>0 &&
            !Objects.equals(city, userInfo.getCity())) {
                userInfo.setCity(city);
            }  if (emergencyPhoneNo!= null &&
                    emergencyPhoneNo.length()>4 &&
                    !Objects.equals(emergencyPhoneNo, userInfo.getEmergencyPhoneNo())) {
                userInfo.setEmergencyPhoneNo(emergencyPhoneNo);
            }
            userInfoRepo.save(userInfo);
            }   else {
            throw new UsernameNotFoundException(email);
        } }

    public UserProfileDataDTO getUserDTOById(Integer userId) throws UserNotFoundException {
        Optional<RoleInfo> userInfoOptional = userInfoRepo.findById(userId);

        if (userInfoOptional.isPresent()) {
            RoleInfo userInfo = userInfoOptional.get();
            return UserProfileDataDTO.mapToDTO(userInfo);
        }

      throw new UserNotFoundException(constants.USER_NOT_FOUND);
    }

    public RoleInfo getUserById2(Integer userId) {
        return userInfoRepo.findById(userId).orElse(null);
    }
    public UserRole getUserById(Integer userId) {
        return userRoleRepo.findById(userId).orElse(null);
    }

//    public List<RoleInfo> getAllUsers() {
//       return userInfoRepo.findAll();
//
//    }
public List<UserProfileDataDTO> getAllUsers() {
    List<RoleInfo> roles = userInfoRepo.findAll(); // Assuming userInfoRepo returns a list of RoleInfo objects
    List<UserProfileDataDTO> userProfiles = new ArrayList<>();

    for (RoleInfo role : roles) {
        UserProfileDataDTO userProfileDTO = userProfileDataDTO.mapToDTO(role); // Assuming userProfileDataDTO has a method mapToDTO(RoleInfo role)
        userProfiles.add(userProfileDTO);
    }

    return userProfiles;
}
  public RoleMain getRole(String email)
  {
      RoleInfo  user = userInfoRepo.findByEmail(email).get();
      System.out.println("\n\n\n"+user.getRole());
      return user.getRole();
  }

    public void UpdateRoleInfoById(Integer id, String fname, String lname, String password, String bloodType, String phoneNo, String city, String emergencyPhoneNo) {
        Optional<RoleInfo> optionalUserInfo = userInfoRepo.findById(id);
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
            } if (phoneNo!= null &&
                    phoneNo.length()>4 &&
                    !Objects.equals(phoneNo, userInfo.getPhoneNo())){
                userInfo.setPhoneNo(phoneNo);
            } if (city!= null &&
                    city.length()>0 &&
                    !Objects.equals(city, userInfo.getCity())) {
                userInfo.setCity(city);
            }  if (emergencyPhoneNo!= null &&
                    emergencyPhoneNo.length()>4 &&
                    !Objects.equals(emergencyPhoneNo, userInfo.getEmergencyPhoneNo())) {
                userInfo.setEmergencyPhoneNo(emergencyPhoneNo);
            }
            userInfoRepo.save(userInfo);
        }   else {
            //test this one. it is pre-built
            throw new UsernameNotFoundException(constants.USER_NOT_FOUND);
        } }

    public void deleteUserById(Integer id) {
        Boolean exists = userInfoRepo.findById(id).isPresent();
        if (exists)
            userInfoRepo.deleteById(id);
        else
            System.out.println(constants.NOT_SUCCESSFUL);
    }

    public UserProfileDataDTO getUserDTOByEmail(String email) throws UserNotFoundException{

        Optional<RoleInfo> userInfoOptional = userInfoRepo.findByEmail(email);

        if (userInfoOptional.isPresent()) {
            RoleInfo userInfo = userInfoOptional.get();
            return UserProfileDataDTO.mapToDTO(userInfo);
        }

        throw new UserNotFoundException(constants.USER_NOT_FOUND);
    }

}
