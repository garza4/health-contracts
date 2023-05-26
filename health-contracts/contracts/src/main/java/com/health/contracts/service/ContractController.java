package com.health.contracts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.contracts.model.FundReq;

@RestController
public class ContractController {
	@Autowired
	ContractImpl contract;

	@PostMapping(value = "/contract/fund-request")
	public Boolean requestFunds(FundReq req) {
		return contract.requestFunds(req);
	}

}
