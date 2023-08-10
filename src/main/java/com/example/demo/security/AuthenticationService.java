package com.example.demo.security;

import com.example.demo.constants;
import com.example.demo.enums.RoleMain;
import com.example.demo.models.RoleInfo;
import com.example.demo.repositories.UserInfoRepo;
import com.example.demo.services.EmailService;
import com.example.demo.services.JwtService;
//import com.example.demo.token.TokenBlacklist;
import com.example.demo.services.UserInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
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
                    .role(request.getRole())//RoleMain.USER   RoleMain.valueOf(request.getRole())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            System.out.println(user);
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
//            revokeAllUserTokens(user);
//            saveUserToken(user, jwtToken);
            emailService.sendRegistrationConfirmationEmail(request.getEmail(), request.getFname());
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        }
        else{
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
//        Integer id = userInfoService.getId()
//        authenticationResponse.setRole(role);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role(role)
                .email(request.getEmail())
                //add fname here?
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
            //filterChain.doFilter(request, response);
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);//todo extract the userEmail from JWT token
        if (userEmail!=null) // && SecurityContextHolder.getContext().getAuthentication()==null){
        {
            var userDetails = this.repository.findByEmail(userEmail)
                    .orElseThrow();

//        var isTokenValid = tokenRepository.findByToken(refreshToken)
//                .map(t -> !t.isExpired() && !t.isRevoked())
//                .orElse(false);
            if (jwtService.isTokenValid(refreshToken, userDetails)) //&& jwtService.isTokenValid())
         {
             var accessToken = jwtService.generateToken(userDetails);
             String role = userDetails.getRole().toString();
//             revokeAllUserTokens(userDetails);
//             saveUserToken(userDetails, accessToken);
             var authResponse = AuthenticationResponse.builder()
                     .accessToken(accessToken)
                     .refreshToken(refreshToken)
                     .role(role)
                     .build();
             new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
         } }
        } }
//    private void saveUserToken(RoleInfo user, String jwtToken) {
//        var token = RoleInfo.builder()
//                .user(user.getUser())
//                .role(jwtToken)
//                .tokenType("Bearer ")
//                .expired(false)
//                .revoked(false)
//                .build();
//        repository.save(token);
//    }
// i am not saving tokens to the db
//    private void revokeAllUserTokens(RoleInfo user) {
//        var validUserTokens = repository.findAllById(Collections.singleton(user.getId()));
//        if (validUserTokens.isEmpty())
//            return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        repository.saveAll(validUserTokens);
//    }