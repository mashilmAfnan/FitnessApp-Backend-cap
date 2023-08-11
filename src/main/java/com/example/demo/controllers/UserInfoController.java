package com.example.demo.controllers;

import com.example.demo.DTO.UserProfileDataDTO;
import com.example.demo.constants;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.RoleInfo;
import com.example.demo.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserInfoController {
    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }
    private UserInfoService userInfoService;
//    @PutMapping("/update-info/{email}")
//    public ResponseEntity<String> UpdateInfoByEmail( @PathVariable("email") String email,
////                            @RequestParam(required = false, defaultValue = "*******")
//                            @RequestBody(required = false)  String fname,
//                            @RequestBody(required = false) String lname,
//                            @RequestBody(required = false) String password,
//                            @RequestBody(required = false) String bloodType,
//                            @RequestBody(required = false) String phoneNo,
//                            @RequestBody(required = false) String city,
//                            @RequestBody(required = false) String emergencyPhoneNo
//                            ) {
//        userInfoService.UpdateRoleInfoByEmail(email, fname, lname, password, bloodType, phoneNo,city,emergencyPhoneNo);
//        return ResponseEntity.ok(constants.INFO_UPDATED_SUCCESSFULLY);
//    }
@PutMapping("/update-info/{email}")
public ResponseEntity<String> updateInfoByEmail(
        @PathVariable("email") String email,
        @RequestBody UserProfileDataDTO userProfileDataDTO) {

    userInfoService.updateRoleInfoByEmail(userProfileDataDTO);
    return ResponseEntity.ok(constants.INFO_UPDATED_SUCCESSFULLY);
}

    @PutMapping("/update-user-info/{id}")
    public ResponseEntity<String> UpdateInfoById( @PathVariable("id") Integer id,
//                            @RequestParam(required = false, defaultValue = "*******")
                                                     @RequestParam(required = false)  String fname,
                                                     @RequestParam(required = false) String lname,
                                                     @RequestParam(required = false) String password,
                                                     @RequestParam(required = false) String bloodType,
                                                     @RequestParam(required = false) String phoneNo,
                                                     @RequestParam(required = false) String city,
                                                     @RequestParam(required = false) String emergencyPhoneNo
    ) {
        userInfoService.UpdateRoleInfoById(id, fname, lname, password, bloodType, phoneNo,city,emergencyPhoneNo);
        return ResponseEntity.ok(constants.INFO_UPDATED_SUCCESSFULLY);
    }
    @DeleteMapping("/delete-user/{id}")
       public ResponseEntity<String> deleteUserById( @PathVariable("id") Integer id){
        userInfoService.deleteUserById(id);
        return ResponseEntity.ok(constants.DELETED_SUCCESSFULLY);
    }
    @GetMapping("/get-user2/{id}")
    public ResponseEntity<UserProfileDataDTO> getUserById1(@PathVariable("id") Integer id) throws UserNotFoundException {
        UserProfileDataDTO userDTO = userInfoService.getUserDTOById(id);
        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/get-email/{email}")
    public ResponseEntity<UserProfileDataDTO> getUserById2(@PathVariable("email") String email) throws UserNotFoundException {
        UserProfileDataDTO userDTO = userInfoService.getUserDTOByEmail(email);
        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/get-all-users")
    public List<UserProfileDataDTO> getAllUsers( ){
      return  userInfoService.getAllUsers();
    }
}
