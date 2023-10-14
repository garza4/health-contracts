/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.health.contracts.service.controller;

import com.health.contracts.config.JwtUtil;
import com.health.contracts.dto.AuthenticationRequest;
import com.health.contracts.model.HealthUserDetails;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Bobby
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtils;
    private final PasswordEncoder PasswordEncoder;
    private final AuthenticationManager AuthenticationManager;

    
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest req){
        log.info("request is: " + req.toString());
        try{
            AuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUid(),req.getPassword()));
            final HealthUserDetails user = (HealthUserDetails) userDetailsService.loadUserByUsername(req.getUid());
            log.info("got user: " + user.toString());
            if(user != null && PasswordEncoder.matches(req.getPassword(), user.getPassword())){
                log.info("user is not null");
                HttpCookie cookie = ResponseCookie.from("health-contracts",jwtUtils.generateToken(user)).path("/authenticate").build();
                return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("login success");
            }
        }catch(Exception e){
            log.error("exception authenticating user",e);
        }
        
        return ResponseEntity.status(400).body("user not authorized");
    }
    
}
