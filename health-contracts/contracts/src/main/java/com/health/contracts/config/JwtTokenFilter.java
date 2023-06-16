/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.health.contracts.config;

import com.health.contracts.entity.HealthUser;
import com.health.contracts.model.HealthUserDetails;
import com.health.contracts.service.UserImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author Bobby
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter{

    private final UserImpl userImpl;
    private final JwtUtil jwtUtils;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, 
            HttpServletResponse response, 
            FilterChain filterChain) throws ServletException, IOException {
        log.debug("" + request.getHeaderNames());
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        Boolean headerisNull = header == null;
        final String uid;
        final String jwtToken;
        
        if(headerisNull){
            filterChain.doFilter(request,response);
            return;
        }
        jwtToken = header.substring(7);
        uid = jwtUtils.extractUsername(jwtToken);
        log.debug("uid is: " + uid);
        if(uid != null && SecurityContextHolder.getContext().getAuthentication() != null){
            HealthUser userDetails = userImpl.getUsers(uid);
            HealthUserDetails hUserDetails = new HealthUserDetails();
            hUserDetails.setUser(userDetails);
            hUserDetails.setUserName(userDetails.getUName());
//            hUserDetails.setPassword()
            if(jwtUtils.validateToken(jwtToken, hUserDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,hUserDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
    
}
