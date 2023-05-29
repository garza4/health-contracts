//package com.health.contracts.filters;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.health.contracts.UserContext;
//import com.health.contracts.entity.HealthUser;
//import com.health.contracts.service.UserImpl;
//import com.health.contracts.session.Session;
//import com.health.contracts.session.SessionManager;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.User;
//
//@Component
//@Slf4j
//public class SecurityFilter extends OncePerRequestFilter{
//	UserImpl userImpl;
//	
////	private AuthenticationManager authManager;
////	@Autowired
////	public SecurityFilter(UserImpl userImpl) {
////		this.userImpl = userImpl;
////	}
//	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		UserContext context = (UserContext)auth.getCredentials();
//		User user  = getUser(request.getHeader("uid"));
//		UserContext userContext = UserContext.builder().uid(request.getHeader("uid")).build();
//		final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,userContext,user.getAuthorities());
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		
//		Session session = Session.builder()
//				.roleName(user.getAuthorities().toString())
//				.expires(1262274L)
//				.build();
//		SessionManager.cache(request.getHeader("uid"),session);
//	}
//	
//	private User getUser(String token) {
//		List<GrantedAuthority> grantList = new ArrayList<>();
//		try {
//			if (token.isBlank()) {
//				log.error("user cannot have empty username");
//				 return null;
//			}
//			HealthUser hUser = userImpl.getUsers(token);
//			if(hUser != null) {
//				grantList.add(buildAuthority(hUser));
//			}
//	        
//			
//		}catch(Exception e) {
//			log.error("Could not get user from db");
//		}
//		final User user = new User(token,"null",true,true,true,true, grantList);
//		return user;  
//    }
//	
//	private SimpleGrantedAuthority buildAuthority(final HealthUser userDetails) {
//		log.info("user details: " + userDetails.toString());
//		return new SimpleGrantedAuthority("ROLE_"+userDetails.getRole());		
//	}
//
//}
