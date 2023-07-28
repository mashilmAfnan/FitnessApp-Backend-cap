//package com.example.demo.controllers;
//
//import com.example.demo.token.TokenValidationFilter;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//
//@RestController
//@RequestMapping("/api/logout")
//public class LogoutController {
//
//    private final TokenValidationFilter tokenValidationFilter;
//
//    public LogoutController(TokenValidationFilter tokenValidationFilter) {
//        this.tokenValidationFilter = tokenValidationFilter;
//    }
//
//    @PostMapping
//    public ResponseEntity<String> logout(HttpServletRequest request) {
//        String token = getTokenFromRequest(request);
//        if (token != null) {
//            tokenValidationFilter.logout(token);
//            return ResponseEntity.ok("Logout successful.");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
//        }
//    }
//
//    private String getTokenFromRequest(HttpServletRequest request) {
//        // Extract the token from the request (e.g., from Authorization header)
//        String authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            return authorizationHeader.substring(7);
//        }
//        return null;
//    }
//}
