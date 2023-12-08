package com.health.contracts.service.controller;

import com.health.contracts.service.impl.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.health.contracts.entity.HealthUser;
import com.health.contracts.model.SaveUserReq;

@Controller
public class UserController {
	private Users userImpl;

	@Autowired
	public UserController(Users userImpl) {
		this.userImpl = userImpl;
	}

	@GetMapping("/users/get/{uid}")
	public HealthUser getUser(@PathVariable String uid) {
		return userImpl.getUsers(uid);
	}
	
	@PutMapping("/users/save")
	public void saveUser(@RequestBody SaveUserReq req) {
		userImpl.saveUser(req);
	}

}
