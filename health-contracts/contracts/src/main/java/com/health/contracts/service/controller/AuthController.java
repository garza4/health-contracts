/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.health.contracts.service.controller;

import com.health.contracts.config.JwtUtil;
import com.health.contracts.dto.AuthenticationRequest;
import com.health.contracts.model.HealthUserDetails;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    private AuthenticationManager authenticationManager;

    private SecurityContextHolderStrategy securityContextHolderStrategy;


    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest req,HttpServletRequest request, HttpServletResponse response){
        log.info("request is: " + req.toString());
        try{
            final HealthUserDetails user = (HealthUserDetails) userDetailsService.loadUserByUsername(req.getUid());

            log.info("got user: " + user.toString());
            if(user != null && PasswordEncoder.matches(req.getPassword(), user.getPassword())){
                log.info("user is not null");
                HttpCookie cookie = ResponseCookie.from("Bearer",jwtUtils.generateToken(user)).path("/").httpOnly(true).maxAge(600).build();
                UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(req.getUid(), req.getPassword());
                return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("login success");
            }
        }catch(Exception e){
            log.error("exception authenticating user",e);
        }
        
        return ResponseEntity.status(400).body("user not authorized");
    }

    private SimpleGrantedAuthority getGrantedAuthority(String role){
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority("ROLE_"+role);
        return auth;
    }
    
}
