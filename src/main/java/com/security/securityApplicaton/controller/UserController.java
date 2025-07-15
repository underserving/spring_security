package com.security.securityApplicaton.controller;


import com.security.securityApplicaton.dtos.UserDto;
import com.security.securityApplicaton.models.Users;
import com.security.securityApplicaton.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServices service;


    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody Users user){
        Users user1=service.registerUser(user);
        UserDto userdto=new UserDto(user1.getUserId(),user1.getUserName());
        return ResponseEntity.ok(userdto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Users user){
        String token=service.verifyUser(user);
        if(token != "fail") {
//            UserDto dto = new UserDto(user.getUserId(), user.getUserName());
            return ResponseEntity.ok(token);
        }
        return new ResponseEntity<>("register", HttpStatus.NOT_FOUND);
    }
}
