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

;import static com.example.demo.security.Permission.USER_UPDATE_INFO;
import static org.springframework.http.HttpMethod.*;

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
            .antMatchers("/api/v1/auth/**").permitAll()
            .antMatchers("/api/v1/user/**").permitAll()
            .antMatchers(GET, "/api/v1/place/**").permitAll()
            .antMatchers("/api/v1/admin-type/**").hasAnyRole("SUPERADMIN")
            .antMatchers(POST, "/api/v1/place/**").hasAnyRole("SUPERADMIN")
            .antMatchers(DELETE, "/api/v1/place/**").hasAnyRole("SUPERADMIN")
            .antMatchers("/api/v1/discount/**").hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers(GET, "/api/v1/feedback/**").hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers(DELETE, "/api/v1/feedback/**").hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers("/api/v1/package/**").hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers("/api/v1/room/**").hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers(PUT, "/api/v1/place/**").hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers("/api/v1/session/**").hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers("/api/v1/room/**").hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers("/api/v1/amenity/**").hasAnyRole("SUPERADMIN", "GYMADMIN") //ONLY GYM ADMIN OF ADMINS
            .antMatchers("/api/v1/subscriber/**").hasAnyRole("SUPERADMIN", "ADMIN") //might need more restrictions
            .antMatchers("/api/v1/discount/**").hasAnyRole("SUPERADMIN", "ADMIN")
            //USER ONLY
            .antMatchers(GET, "/api/v1/amenity/**").hasAnyRole("USER")
            .antMatchers(POST,  "/api/v1/feedback/**").hasAnyRole("USER") //should i add one for GET?
            .antMatchers(GET,"/api/v1/package/**").hasAnyRole("USER")
            .antMatchers(GET,"/api/v1/room/**").hasAnyRole("USER")
            .antMatchers(GET,"/api/v1/session/**").hasAnyRole("USER")
            .antMatchers(PUT, "/api/v1/user/**" ).hasAuthority(USER_UPDATE_INFO.name())

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
/**/


    private RequestMatcher requestMatcher(String pathPattern) {
        return new AntPathRequestMatcher(pathPattern);
    }



}
//1. permit all
//2. sadmin only
//3. sadmin and admin(all)
//4. admin (all) only
//5. specific admin
//6. admib=n with user
//7. user only