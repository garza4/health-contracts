package com.health.contracts.service.impl;

import com.health.contracts.model.*;

public interface Account {
	public StellarAccount createAccount();

	public String resetAccount();

	public String fundAccount();

	public BalanceList checkBalance();

	public AddUserToSystemResp addUserToSystem(AddUserToSystemReq req);
}
