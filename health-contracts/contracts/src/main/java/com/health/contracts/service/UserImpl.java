package com.health.contracts.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.health.contracts.entity.HealthUser;
import com.health.contracts.repository.UserRepository;

public class UserImpl implements Users{
	@Autowired
	UserRepository userRepo;
	public UserImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public HealthUser getUsers(String uid) {
		// TODO Auto-generated method stub
		return userRepo.findByUid(uid);
	}

}
