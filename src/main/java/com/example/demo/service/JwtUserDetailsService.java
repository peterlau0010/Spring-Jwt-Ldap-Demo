package com.example.demo.service;

//import org.springframework.security.core.userdetails.User;

import com.example.demo.model.User;

public class JwtUserDetailsService {

    public User loadUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return user;
    }
}
