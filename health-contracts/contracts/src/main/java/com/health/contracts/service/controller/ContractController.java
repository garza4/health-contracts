package com.health.contracts.service.controller;


import com.health.contracts.service.impl.ContractApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.health.contracts.model.FundReq;
import com.health.contracts.model.ReqFundsResp;
import com.health.contracts.service.impl.ContractImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ContractController {
	@Autowired
	ContractApi contract;

	@PostMapping(value = "/contract/fund-request")
	public ReqFundsResp requestFunds(@RequestBody FundReq req) {
		log.info("Requesting funds for {} ",req.toString());
		return contract.requestFunds(req);
	}

}
