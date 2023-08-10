package com.example.demo.DTO;

import com.example.demo.models.RoleInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class UserProfileDataDTO implements Serializable {
    //HERE I SHOULD RETURN THE DATA THAT HTE USER CAN VIEW AND EDIT (UPDATE)
    private String fname;
    private String lname;
    private String bloodType;
    private String city;
    private String email;
    private String phoneNo;
    private String emergencyPhoneNo;
    private Integer id;


    public static UserProfileDataDTO mapToDTO(RoleInfo roleInfo) {
        UserProfileDataDTO userDTO = new UserProfileDataDTO();
        userDTO.setFname(roleInfo.getFname());
        userDTO.setLname(roleInfo.getLname());
        userDTO.setBloodType(roleInfo.getBloodType());
        userDTO.setCity(roleInfo.getCity());
        userDTO.setPhoneNo(roleInfo.getPhoneNo());
        userDTO.setEmergencyPhoneNo(roleInfo.getEmergencyPhoneNo());
        userDTO.setEmail(roleInfo.getEmail());
        userDTO.setId(roleInfo.getId());
        return userDTO;
    }
}
