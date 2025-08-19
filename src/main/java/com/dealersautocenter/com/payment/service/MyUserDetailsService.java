package com.dealersautocenter.com.payment.service;


import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService {

    // Simple dummy user validation
    public boolean validateUser(String username, String password) {
        return "user".equals(username) && "pass".equals(password);
    }
}
