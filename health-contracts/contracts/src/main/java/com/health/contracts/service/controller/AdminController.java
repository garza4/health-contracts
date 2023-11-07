package com.health.contracts.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.health.contracts.model.LogVisitationReq;
import com.health.contracts.model.VisitationLog;
import com.health.contracts.service.impl.AdminImpl;
import com.health.contracts.service.impl.Users;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class AdminController {
	@Autowired
	private AdminImpl adminImpl;
        
	@PutMapping(value="/admin/log-visit")
	public void logVisitation(@RequestBody LogVisitationReq req) {
            adminImpl.logVisitation(req);
	}
        
        @GetMapping(value="/admin/get-visits/{uid}")
        public VisitationLog getVisits(@PathVariable String uid){
            return adminImpl.getPendingVisits(uid);
        }
}
