package com.security.securityApplicaton.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  int userId;
   private String userName;
   private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
