package com.health.contracts.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.health.contracts.UserContext;
import com.health.contracts.entity.User;
import com.health.contracts.service.UserImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HeaderAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	UserImpl userImpl;
	
	private AuthenticationManager authManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserContext context = (UserContext)auth.getCredentials();
	}
	
	private User getUser(String token) {
		User user = null;
		try {
			if (token.isBlank()) {
				log.error("user cannot have empty username");
				 return null;
			}
	        
	        user = userImpl.getUsers(token);
			
		}catch(Exception e) {
			log.error("Could not get user from db");
		}
		return user;  
    }

}
