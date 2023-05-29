//package com.health.contracts.security;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import com.health.contracts.UserContext;
//
//@Component
//public class UserAuthenticationFacade implements IAuthenticationFacade{
//	
//	@Override
//	public UserContext getUserContext() {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		UserContext context = (UserContext)auth.getCredentials();
//		return context;
//	}
//	
//}
