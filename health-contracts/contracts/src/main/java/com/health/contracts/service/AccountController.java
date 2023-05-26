package com.health.contracts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.contracts.model.BalanceList;
import com.health.contracts.model.CheckFundsReq;
import com.health.contracts.model.StellarAccount;

@RestController
public class AccountController {
	@Autowired
	AccountImpl accountImpl;

	@PostMapping(value = "/account/balance")
	public BalanceList checkBalance(CheckFundsReq req) {
		return accountImpl.checkBalance(req.getAccountId());
	}

	@GetMapping("/account/create")
	public StellarAccount createAccount() {
		return accountImpl.createAccount();
	}

}
