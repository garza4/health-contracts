package com.health.contracts.service.controller;

import com.health.contracts.model.AddUserToSystemReq;
import com.health.contracts.service.impl.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.health.contracts.model.BalanceList;
import com.health.contracts.model.StellarAccount;

@RestController
public class AccountController {
	@Autowired
	Account accountImpl;

	@GetMapping(value = "/account/balance")
	public BalanceList checkBalance() {
		return accountImpl.checkBalance();
	}

	@GetMapping(value="/account/create")
	public StellarAccount createAccount() {
		return accountImpl.createAccount();
	}

	@PutMapping(value="/account/register")
	public void registerUserToSystem(@RequestBody AddUserToSystemReq req){
		accountImpl.addUserToSystem(req);
	}

}
