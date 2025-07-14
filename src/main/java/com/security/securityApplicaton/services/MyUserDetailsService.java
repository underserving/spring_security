package com.security.securityApplicaton.services;

import com.security.securityApplicaton.models.UserPrincipal;
import com.security.securityApplicaton.models.Users;
import com.security.securityApplicaton.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

//    @Autowired
    private UserRepo repo;

    public MyUserDetailsService(UserRepo repo){
        this.repo=repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Users user=repo.findByUserName(username);

        if(user==null){
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user);
    }
//    public UserDetails getUser
}
