package com.dealersautocenter.com.payment.controller;

import com.dealersautocenter.com.payment.service.MyUserDetailsService;
import com.dealersautocenter.com.payment.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        if (userDetailsService.validateUser(authRequest.getUsername(), authRequest.getPassword())) {
            final String jwt = jwtUtil.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(jwt));
        } else {
            return ResponseEntity.status(401).body("Invalid Credentials");
        }
    }
}

class AuthRequest {
    private String username;
    private String password;

    // getters & setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

class AuthResponse {
    private final String jwt;

    public AuthResponse(String jwt) { this.jwt = jwt; }
    public String getJwt() { return jwt; }
}

