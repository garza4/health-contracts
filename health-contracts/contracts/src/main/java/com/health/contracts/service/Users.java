package com.health.contracts.service;

import com.health.contracts.entity.HealthUser;
import com.health.contracts.model.SaveUserReq;

public interface Users {
	HealthUser getUsers(String uid);
	
	void saveUser(SaveUserReq user);

}
