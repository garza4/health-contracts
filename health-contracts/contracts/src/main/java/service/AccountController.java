package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import model.BalanceList;
import model.CheckFundsReq;
import model.StellarAccount;


@RestController
public class AccountController {
	@Autowired
	AccountImpl accountImpl;
	
	@PostMapping(value = "/account/balance")
	public BalanceList checkBalance(CheckFundsReq req) {
		return accountImpl.checkBalance(req.getAccountId());
	}
	
	@GetMapping(value="/account/create")
	public StellarAccount createAccount() {
		return accountImpl.createAccount();
	}

}
