package com.example.demo.config;

//import com.example.demo.token.TokenBlacklist;
//import com.example.demo.token.TokenValidationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

;import static com.example.demo.enums.Permission.USER_UPDATE_INFO;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

 private final JwtAuthenticationFilter jwtAuthFilter;
 private final AuthenticationProvider authenticationProvider;
//    private final TokenBlacklist tokenBlacklist;
//    private final   TokenValidationFilter tokenValidationFilter;

 @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().
        disable()
        .authorizeRequests()
            .requestMatchers(requestMatcher("/api/v1/auth/**"))
            .permitAll()
            .antMatchers("/api/v1/user/**")
            .permitAll()
            .antMatchers(GET, "/api/v1/place/**")
            .permitAll()
            .antMatchers("/api/v1/admin-type/**")
            .hasAnyRole("SUPERADMIN")
            .antMatchers(POST, "/api/v1/place/**")
            .hasAnyRole("SUPERADMIN")
            .antMatchers(DELETE, "/api/v1/place/**")
            .hasAnyRole("SUPERADMIN")
            .antMatchers("/api/v1/discount/**")
            .hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers(GET, "/api/v1/feedback/**")
            .hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers(DELETE, "/api/v1/feedback/**").hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers("/api/v1/package/**").hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers("/api/v1/room/**").hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers(PUT, "/api/v1/place/**").hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers("/api/v1/session/**").hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers("/api/v1/room/**").hasAnyRole("SUPERADMIN", "ADMIN")
            .antMatchers("/api/v1/amenity/**").hasAnyRole("SUPERADMIN", "GYMADMIN") //ONLY GYM ADMIN OF ADMINS
            .antMatchers("/api/v1/subscriber/**").hasAnyRole("SUPERADMIN", "ADMIN") //might need more restrictions and user should add this
            .antMatchers("/api/v1/discount/**").hasAnyRole("SUPERADMIN", "ADMIN")
            //USER AND SUBSCRIBER ONLY
            .antMatchers(GET, "/api/v1/amenity/**").hasAnyRole("USER")
            .antMatchers(POST,  "/api/v1/feedback/**").hasAnyRole("SUBSCRIBER") //ONLY  a subscriber can write feedbacks //should i add one for GET?
            .antMatchers(GET,"/api/v1/package/**").hasAnyRole("USER")
            .antMatchers(GET,"/api/v1/room/**").hasAnyRole("USER")
            .antMatchers(GET,"/api/v1/session/**").hasAnyRole("USER")
            .antMatchers(POST,"/api/v1/sub-sessionbooking/**").hasAnyRole("USER") //add that user should add subscriber!
            .antMatchers(POST, "/api/v1/subscriber/**").hasAnyRole("USER")

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

//    @Bean
//    public TokenValidationFilter tokenValidationFilter(TokenBlacklist tokenBlacklist, AuthenticationManager authenticationManager) {
//        return new TokenValidationFilter(new AntPathRequestMatcher("/**"), tokenBlacklist, authenticationManager);
//    }

}
//1. permit all
//2. sadmin only
//3. sadmin and admin(all)
//4. admin (all) only
//5. specific admin
//6. admib=n with user
//7. user only

 //  .addFilter(new TokenValidationFilter(new AntPathRequestMatcher("/**"), tokenBlacklist, authenticationManager)
//            .addFilterBefore(tokenValidationFilter, UsernamePasswordAuthenticationFilter.class)
//            .logout()
//            .logoutUrl("/api/v1/logout") // Customize the logout URL if needed
//            .invalidateHttpSession(true) // Invalidate the session on logout
////            .deleteCookies("JSESSIONID") // Delete the session cookie on logout
////            .logoutSuccessUrl("/logout-success") // Customize the logout success URL
//            .permitAll();
//    or add this
//             .addLogoutHandler(logoutHandler)
//             .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())


//from bouali
//  .logout()
//          .logoutUrl("/api/v1/auth/logout")
//          .addLogoutHandler(logoutHandler)
//          .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//          ;