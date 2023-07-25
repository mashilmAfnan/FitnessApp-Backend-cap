package com.example.demo.security;

import com.example.demo.constants;
import com.example.demo.models.Role;
import com.example.demo.models.RoleInfo;
import com.example.demo.models.RoleInfo;
import com.example.demo.repositories.UserInfoRepo;
import com.example.demo.services.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.Collections;
import java.util.Optional;
// dont want to save tokens to the db
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserInfoRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        //we dont want any email duplicates
        Boolean exists = repository.existsByEmail(request.getEmail());
        if (!exists) {
            var user = RoleInfo.builder()
                    .fname(request.getFname())
                    .lname(request.getLname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
//            revokeAllUserTokens(user);
//            saveUserToken(user, jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        }
        else{
            throw new DuplicateRequestException(constants.DUPLICATE_EMAIL);
        }
    }
//should  i add refresh token?


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

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
//              .orElseThrow(() -> new UserNotFoundException(constants.USER_NOT_FOUND_EXCEPTION));
//
//        if (!user.getIsVerified()){
//
//            throw new UnverifiedUserException(constants.UNVERIFIED_USER_EXCEPTION);
//        }
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
        //.message(constants.AUTHENTICATION_SUCCESSFUL)
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
//             revokeAllUserTokens(userDetails);
//             saveUserToken(userDetails, accessToken);
             var authResponse = AuthenticationResponse.builder()
                     .accessToken(accessToken)
                     .refreshToken(refreshToken)
                     .build();
             new ObjectMapper().writeValue(response.getOutputStream(), authResponse);

//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//                //update security context holder
//                SecurityContextHolder.getContext().setAuthentication(authToken);
         }
        }
        }
    }
