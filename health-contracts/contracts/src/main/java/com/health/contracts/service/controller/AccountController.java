package com.health.contracts.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.contracts.model.BalanceList;
import com.health.contracts.model.StellarAccount;
import com.health.contracts.service.impl.AccountImpl;

@RestController
public class AccountController {
	@Autowired
	AccountImpl accountImpl;

	@GetMapping(value = "/account/balance")
	public BalanceList checkBalance() {
		return accountImpl.checkBalance();
	}

	@GetMapping(value="/account/create")
	public StellarAccount createAccount() {
		return accountImpl.createAccount();
	}

}
