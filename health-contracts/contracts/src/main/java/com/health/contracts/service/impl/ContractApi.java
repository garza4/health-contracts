package com.health.contracts.service.impl;

import com.health.contracts.model.FundReq;
import com.health.contracts.model.ReqFundsResp;

public interface ContractApi {
	public ReqFundsResp requestFunds(FundReq req);
}
