/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.health.contracts.config;

import com.health.contracts.entity.HealthUser;
import com.health.contracts.config.JwtUtil;
import com.health.contracts.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author Bobby
 */
@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter{

    private final UserRepository userRepo;
    private JwtUtil jwtUtil;
    
    @Autowired
    public JwtTokenFilter(UserRepository userRepo,JwtUtil jwtUtil){
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
    }
    
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, 
            HttpServletResponse response, 
            FilterChain filterChain) throws ServletException, IOException {
        log.debug("" + request.getHeaderNames());
        if(request.getRequestURI().contains("/auth")){
            log.debug("user is authorized");
            filterChain.doFilter(request,response);
            return;
        }
        try{
            log.debug("jwtAuth");
            String jwtHeader = null;
            //if jwtToken is still valid continue with session
            Cookie[] cookies = request.getCookies();//request.getHeader(HttpHeaders.AUTHORIZATION);
            if(cookies != null && cookies.length != 0){
                for(Cookie singleCookie : cookies){
                    log.debug("cookie : {}",singleCookie.getName());
                    if(singleCookie.getName().equals("Bearer")){
                        jwtHeader = singleCookie.getValue();
                        log.debug("JWT header is: {}",jwtHeader);
//                        String jwtToken = jwtHeader.split(";")[1].trim();
                        if(jwtUtil.validateToken(jwtHeader)){
                         
                            log.debug("jwt token: {}",jwtHeader);
                            String uid = getUserName(jwtHeader);
                            Jwts.builder().setClaims(new HashMap<String,Object>()).setSubject(uid).setIssuedAt(new Date(System.currentTimeMillis()))
                                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                                .signWith(SignatureAlgorithm.HS256, "secret").compact();

                            if(uid.isBlank() || uid ==null){
                                log.error("uid from token invalid");
                                response.sendError(401,"uid from token invalid");
                                return;
                            }else{
                                HealthUser hUser = userRepo.getUserByUName(uid);  
                                if(hUser == null){
                                    log.error("this user does not exist");
                                    response.sendError(401,"could not find user");
                                    return;
                                }
                                List<GrantedAuthority> grantedAuth = new ArrayList();
                                grantedAuth.add(getGrantedAuthority(hUser.getRole()));
                                final UsernamePasswordAuthenticationToken authentication = 
                                        new UsernamePasswordAuthenticationToken(hUser,hUser,grantedAuth);
                                SecurityContextHolder.getContext().setAuthentication(authentication);
                            }
                            filterChain.doFilter(request,response);
                            return;
                        }else{
                            response.sendError(401,"token invalid");
                            return;
                        }
                    }
                }
            }
            
        }catch(Exception e){
            log.error("Error occured while performing filter",e);
            response.sendError(401);
            return;
        }
        response.sendError(401,"unauthorized");
        return;
    }
    
    private String getUserName(String token){
        String uid = Jwts.parser()
                    .setSigningKey("secret")
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        return uid;
    }
    
    private SimpleGrantedAuthority getGrantedAuthority(String role){
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority("ROLE_"+role);
        return auth;
    }
    
}
