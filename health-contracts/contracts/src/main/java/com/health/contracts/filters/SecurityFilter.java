package com.health.contracts.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.health.contracts.UserContext;
import com.health.contracts.session.Session;
import com.health.contracts.session.SessionManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;

@Component
@Slf4j
public class SecurityFilter extends OncePerRequestFilter{
	
	private AuthenticationManager authManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            User user  = getUser(request.getHeader("uid"));
		UserContext userContext = UserContext.builder().uid(request.getHeader("uid")).build();
		final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,userContext,user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Session session = Session.builder()
				.roleName(user.getAuthorities().toString())
				.expires(1262274L)
				.build();
		SessionManager.cache(request.getHeader("uid"),session);
	}
	
	private User getUser(String token) {
		List<GrantedAuthority> grantList = new ArrayList<>();
		try {
                    if (token.isBlank()) {
                            log.error("user cannot have empty username");
                             return null;
                    }
                    
                    grantList.add(buildAuthority(token));
	        	
		}catch(Exception e) {
                    log.error("user was empty",e);
		}
		final User user = new User(token,"null",true,true,true,true, grantList);
		return user;  
    }
	
	private SimpleGrantedAuthority buildAuthority(final String userDetails) {
		log.info("user details: " + userDetails.toString());
		return new SimpleGrantedAuthority("ROLE_"+userDetails);		
	}

}
