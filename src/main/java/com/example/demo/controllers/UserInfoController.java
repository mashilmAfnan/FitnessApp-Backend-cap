package com.example.demo.controllers;

import com.example.demo.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserInfoController {
    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    private UserInfoService userInfoService;
    @PutMapping("/update-user-info/{email}")
    public ResponseEntity<String> UpdateInfo( @PathVariable("email") String email,
//                            @RequestParam(required = false, defaultValue = "*******")
                            @RequestParam(required = false)  String fname,
                            @RequestParam(required = false) String lname,
                            @RequestParam(required = false) String password,
                            @RequestParam(required = false) String bloodType,
                            @RequestParam(required = false) String phoneNo,
                            @RequestParam(required = false) String city,
                            @RequestParam(required = false) String emergencyPhoneNo
                            ) {
        System.out.println("\n\nHello from user controller1!!!!!\n\n");
        userInfoService.UpdateRoleInfo(email, fname, lname, password, bloodType, phoneNo,city,emergencyPhoneNo);
        System.out.println("\n\nHello from user controller2!!!!!\n\n");
                //, lname, password, bloodType, phoneNo,city,emergencyPhoneNo );
        return ResponseEntity.ok("Information updated successfully.");

        //do i add log out here?
    }
}
