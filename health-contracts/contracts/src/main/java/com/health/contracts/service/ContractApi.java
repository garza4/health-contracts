package com.health.contracts.service;

import com.health.contracts.model.FundReq;

public interface ContractApi {
	public Boolean requestFunds(FundReq req);
}
