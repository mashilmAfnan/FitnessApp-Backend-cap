package com.example.demo.services;

import com.example.demo.constants;
import com.example.demo.models.RoleInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
//    @Value("${application.security.jwt.secret-key}")
//    private String jwtSecretKey;
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
     final Claims claims = extractAllClaims(token);
     return claimResolver.apply(claims);
    }
    private Claims extractAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String generateToken(UserDetails userDetails){
        Map<String, Object> extraClaims = new HashMap<>();

//   /////      Extract the user's role from the UserDetails object
        if (userDetails instanceof RoleInfo) {
            RoleInfo roleInfo = (RoleInfo) userDetails;
            extraClaims.put("role", roleInfo.getRole().name());
          //  System.out.println("**********************role info:  " + roleInfo.toString());
        }
        return generateToken(extraClaims, userDetails);
       // return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return buildToken(extraClaims, userDetails, constants.SECRET_KEY_EXPIRATION);
    }
  private String buildToken(
          Map<String, Object> extraClaims,
          UserDetails userDetails,
          Integer exp
  ){
      return Jwts
              .builder()
              .setHeaderParam("typ", "JWT")
              .setClaims(extraClaims)
              .setSubject(userDetails.getUsername())
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis()+ exp))
              .signWith(getSignInKey(), SignatureAlgorithm.HS256)
              .compact();
  }
    public String generateRefreshToken(
           UserDetails userDetails

    ){
        return buildToken(new HashMap<>(),  userDetails, constants.REFRESH_TOKEN_EXPIRATION);
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username =extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(constants.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
