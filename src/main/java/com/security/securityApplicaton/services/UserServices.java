package com.security.securityApplicaton.services;


import com.security.securityApplicaton.models.Users;
import com.security.securityApplicaton.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserRepo repo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwt;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public Users registerUser(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }


    public Boolean verifyUser(Users user) {
        Authentication authenctication=
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));

        String token= jwt.generateToken(user);
        System.out.println(token);
        if(authenctication.isAuthenticated()){
            return true;
        }
        return false;
    }

//    public Users loginUser(Users user){
//
//    }
}
