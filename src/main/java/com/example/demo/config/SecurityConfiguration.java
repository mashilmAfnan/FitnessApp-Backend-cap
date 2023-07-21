package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

;import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
 private final JwtAuthenticationFilter jwtAuthFilter;
 private final AuthenticationProvider authenticationProvider;

//.requestMatchers("/api/v1/auth/**").hasAnyRole(SUPER_ADMIN.name())
 @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().
        disable()
        .authorizeRequests()
            .requestMatchers(requestMatcher("/api/v1/auth/**"))
            .permitAll()
            .antMatchers("/api/v1/amenity/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
            .antMatchers("/api/v1/package/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
            .antMatchers("/api/v1/room/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
            .antMatchers("/api/v1/discount/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
            .antMatchers(GET, "/api/v1/amenity/**").hasAnyRole("USER")
            .antMatchers(GET,"/api/v1/package/**").hasAnyRole("USER")
            .antMatchers(GET,"/api/v1/room/**").hasAnyRole("USER")
            .antMatchers(GET,"/api/v1/discount/**").hasAnyRole("USER")

            .anyRequest()
            .authenticated()
            .and()
        .sessionManagement().
        sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    http.cors().disable();
    http.csrf().disable();

    return http.build();
}



    private RequestMatcher requestMatcher(String pathPattern) {
        return new AntPathRequestMatcher(pathPattern);
    }



}
