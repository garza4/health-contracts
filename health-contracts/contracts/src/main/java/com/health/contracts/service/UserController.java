package com.health.contracts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.health.contracts.entity.HealthUser;

public class UserController {
	@Autowired
	UserImpl userImpl;
	
	@GetMapping("/users/get/{uid}")
	public HealthUser getUser(@PathVariable String uid) {
		return userImpl.getUsers(uid);
	}

}
