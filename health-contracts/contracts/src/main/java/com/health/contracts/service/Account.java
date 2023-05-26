package com.health.contracts.service;

import com.health.contracts.model.BalanceList;
import com.health.contracts.model.StellarAccount;

public interface Account {
	public StellarAccount createAccount();

	public String resetAccount();

	public String fundAccount();

	public BalanceList checkBalance(String stellarAcct);
}
