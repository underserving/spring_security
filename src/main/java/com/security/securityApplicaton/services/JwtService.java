package com.security.securityApplicaton.services;

import com.security.securityApplicaton.config.JwtConfig;
//import com.security.securityApplicaton.dtos.UserDto;
import com.security.securityApplicaton.models.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Autowired
    private JwtConfig config;


    public String generateToken(Users user){

       // we are using map here just to add custom roles here like "role" : "user" , "email":"ad@adfka"
       // like these roles if we are not adding it to the subject. as here the subject is usename
       Map<String,Object> claims=new HashMap<>();

       return Jwts.builder()
               .claims()
               .add(claims)
               .subject(user.getUserName())
               .issuedAt(new Date(System.currentTimeMillis()))
               .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 10))
               .and()
               .signWith(getKey())
               .compact();

   }

   public SecretKey getKey(){
     byte [] sk=  Decoders.BASE64.decode(config.secret());
     return Keys.hmacShaKeyFor(sk);
   }

    public String extractUserDetails(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserDetails(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
