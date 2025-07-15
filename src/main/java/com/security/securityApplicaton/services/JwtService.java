package com.security.securityApplicaton.services;

import com.security.securityApplicaton.dtos.UserDto;
import com.security.securityApplicaton.models.Users;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
   public String generateToken(Users user){
       return "userName: "+ user.getUserName();
   }
}
