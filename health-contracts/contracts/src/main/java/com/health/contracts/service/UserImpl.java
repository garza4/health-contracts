package com.health.contracts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.health.contracts.UserContext;
import com.health.contracts.entity.HealthUser;
import com.health.contracts.repository.UserRepository;

@Component
public class UserImpl implements Users{
	private UserRepository userRepo;
//	private IAuthenticationFacade userContext;
	@Autowired
	public UserImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public HealthUser getUsers(String uid) {
		return userRepo.getReferenceById(uid);
	}

}
