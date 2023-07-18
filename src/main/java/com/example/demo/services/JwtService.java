package com.example.demo.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
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
    private static final String SECRET_KEY="PeTS1Y3gUbkCZJVS6MN/R5fuJyBin3yL6zBw+Ei1Uu2xSq3HMsYsbx6eSSJlItKPky4mSQss8kcuaLKvmSAwS5l1po3qreyZLFyEMxiPR4NQzS3M03G9CcBZg0+nHGQ2+TJGGFnwEA0zfvJgRGVbbfc8eOj7h+V5ZbbNTghrOqr76X2yx866Dc0mMPxfYaxIgo+/pKVKTUb+Y4sOyyu5Y71S7WVkurtFoFRlr2XzjEbQRpKzrp5RWGJDDV+bGo9tUxTiPH3dmUxdYia4Xdj6ujEj8R17UY9tjbT10TiM22onQ9fbPOi75MH5h5ThFbU+SsROMpUQHMGzCM4nCa7rbD3Ny78NXX/hEHOEINXq2Qw=\n";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
     final Claims claims = extractAllClaims(token);
     return claimResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
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

    private Claims extractAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
