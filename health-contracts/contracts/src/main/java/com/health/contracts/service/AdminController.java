package com.health.contracts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.health.contracts.model.LogVisitationReq;

@RestController
public class AdminController {
	@Autowired
	private AdminImpl adminImpl;
	
	@PutMapping(value="/admin/log-visit")
	public void logVisitation(@RequestBody LogVisitationReq req) {
		adminImpl.logVisitation(req);
	}
}
