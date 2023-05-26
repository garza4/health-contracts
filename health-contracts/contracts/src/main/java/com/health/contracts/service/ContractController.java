package com.health.contracts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.health.contracts.model.FundReq;

@Controller
public class ContractController {
	@Autowired
	ContractImpl contract;

	@PostMapping(value = "/contract/fund-request")
	public Boolean requestFunds(FundReq req) {
		return contract.requestFunds(req);
	}

}
