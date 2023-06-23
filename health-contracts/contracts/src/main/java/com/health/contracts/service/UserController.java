package com.health.contracts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.health.contracts.entity.HealthUser;
import com.health.contracts.model.SaveUserReq;
@RestController
public class UserController {
	private UserImpl userImpl;
        
        @Autowired
        public UserController(UserImpl userImpl){
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
