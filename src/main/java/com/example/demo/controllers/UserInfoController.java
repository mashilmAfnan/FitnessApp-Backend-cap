package com.example.demo.controllers;

import com.example.demo.services.UserInfoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserInfoController {
    private UserInfoService userInfoService;
    @PutMapping("/update-user-info/{email}")
    public void UpdateInfo( @PathVariable("email") String email,
                            @RequestParam(required = false) String fname,
                            @RequestParam(required = false) String lname,
                            @RequestParam(required = false) String password,
                            @RequestParam(required = false) String bloodType,
                            @RequestParam(required = false) String phoneNo,
                            @RequestParam(required = false) String city,
                            @RequestParam(required = false) String emergencyPhoneNo
                            ) {
        userInfoService.UpdateInfo(email, fname, lname, password, bloodType, phoneNo,city,emergencyPhoneNo );
    }
}
