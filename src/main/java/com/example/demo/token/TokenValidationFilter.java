//package com.example.demo.token;
//
//import com.example.demo.config.JwtAuthenticationFilter;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
////@Component
//public class TokenValidationFilter extends AbstractAuthenticationProcessingFilter  { //implements LogoutHandler
//
//    private final TokenBlacklist tokenBlacklist;
//
//
//
//
//    public void logout(String token) {
//        tokenBlacklist.addTokenToBlacklist(token);
//    }
//
//    public TokenValidationFilter(RequestMatcher requiresAuthenticationRequestMatcher, TokenBlacklist tokenBlacklist, AuthenticationManager authenticationManager) {
//        super(requiresAuthenticationRequestMatcher, authenticationManager);
//        this.tokenBlacklist = tokenBlacklist;
//    }
//
//    @Override
//    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
//        // This filter is called on every request, so it requires authentication
//        return true;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
//        // Extract and validate the token from the request, create an Authentication object
//        // ...
//
//        // Return the Authentication object
//        // ...
//        return null;
//    }  //doFilterInternal
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        // Check if the token is blacklisted
//        String token = getTokenFromRequest(request);
//        if (tokenBlacklist.isTokenBlacklisted(token)) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is blacklisted");
//            return;
//        }
//
//        // If the token is not blacklisted, proceed with the request
//        chain.doFilter(request, response);
//    }
//// redundant can already be found in the JwtAuthenticationFilter file, doFilterInternal method
//    private String getTokenFromRequest(HttpServletRequest request) {
//        // Extract the token from the request (e.g., from Authorization header)
//        String authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            return authorizationHeader.substring(7);
//        }
//        return null;
//    }
//
//
//}
//save the token to the db with its own id. upon log out delete the token from the db. refresh token is supposed to update the value of the access token