package com.health.contracts.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.health.contracts.UserContext;
import com.health.contracts.entity.HealthUser;

@Component
public class UserAuthenticationFacade implements IAuthenticationFacade{
	
	@Override
	public UserContext getUserContext() {
		HealthUser hUser = (HealthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserContext context = new UserContext();
                context.setUid(hUser.getUName());
		return context;
	}
	
}
