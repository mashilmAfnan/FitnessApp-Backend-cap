package com.example.demo.security;

import com.example.demo.constants;
import com.example.demo.models.RoleInfo;
import com.example.demo.repositories.UserInfoRepo;
import com.example.demo.services.EmailService;
import com.example.demo.services.JwtService;
import com.example.demo.services.UserInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserInfoRepo repository;
    private final UserInfoService userInfoService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;
    private final EmailService emailService;

//    @EventListener(ApplicationReadyEvent.class)
    public AuthenticationResponse register(RegisterRequest request) {
        Boolean exists = repository.existsByEmail(request.getEmail());
        if (!exists) {
          var  user = RoleInfo.builder()
                    .fname(request.getFname())
                    .lname(request.getLname())
                    .email(request.getEmail())
                    .bloodType(request.getBloodType())
                    .phoneNo(request.getPhoneNo())
                    .emergencyPhoneNo(request.getEmergencyPhoneNo())
                    .city(request.getCity())
                    .role(request.getRole())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            System.out.println(user);
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            emailService.sendRegistrationConfirmationEmail(request.getEmail(), request.getFname());
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } else{
             ResponseEntity.status(HttpStatus.BAD_REQUEST).body(constants.DUPLICATE_EMAIL);
            throw new DuplicateRequestException(constants.DUPLICATE_EMAIL);
        }
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        try{

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user2 = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        String role = userInfoService.getRole(request.getEmail()).toString();
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role(role)
                .email(request.getEmail())
                .build();
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
            return null;
        }
    }
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader("AUTHORIZATION");
        final String refreshToken;
       // final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith(constants.START_WITH_BEARER)) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);//todo extract the userEmail from JWT token
        if (userEmail!=null)
        {
            var userDetails = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, userDetails))
         {
             var accessToken = jwtService.generateToken(userDetails);
             String role = userDetails.getRole().toString();
             var authResponse = AuthenticationResponse.builder()
                     .accessToken(accessToken)
                     .refreshToken(refreshToken)
                     .role(role)
                     .build();
             new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
         } }
        } }
