package com.security.securityApplicaton.services;

import com.security.securityApplicaton.dtos.UserDto;
import com.security.securityApplicaton.models.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private String secretKey;

    public JwtService(){
        try {
            KeyGenerator key=KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk=key.generateKey();
            this.secretKey=Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

   public String generateToken(Users user){

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

   public Key getKey(){
     byte [] sk=  Decoders.BASE64.decode(secretKey);
     return Keys.hmacShaKeyFor(sk);
   }
}
