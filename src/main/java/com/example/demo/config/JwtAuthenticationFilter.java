package com.example.demo.config;

import com.example.demo.constants;
import com.example.demo.services.JwtService;
import com.sun.istack.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            @NonNull  HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull    FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader(constants.AUTH_HEADER);
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith(constants.START_WITH_BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);//todo extract the userEmail from JWT token
      if (userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
          UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//          var isTokenValid = tokenRepository.findByToken(jwt)
//                  .map(t -> !t.isExpired() )  to get from db
//                  .orElse(false);
          if (jwtService.isTokenValid(jwt, userDetails)){
              UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                      userDetails,
                      null,
                      userDetails.getAuthorities()
              );
              authToken.setDetails(
                      new WebAuthenticationDetailsSource().buildDetails(request)
              );
              //update security context holder
              SecurityContextHolder.getContext().setAuthentication(authToken);
          }
      }
      filterChain.doFilter(request,response);
    }
}
