package com.health.contracts.service.controller;

import com.health.contracts.model.GetLogsResp;
import com.health.contracts.service.impl.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.health.contracts.model.LogVisitationReq;
import com.health.contracts.model.VisitationLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class AdminController {
	private Admin adminImpl;

    @Autowired
    public AdminController(Admin adminImpl) {
        this.adminImpl = adminImpl;
    }

    @PutMapping(value="/admin/log-visit")
	public void logVisitation(@RequestBody LogVisitationReq req) {
            adminImpl.logVisitation(req);
	}
        
    @GetMapping(value="/admin/get-visits/{uid}")
    public GetLogsResp getVisits(@PathVariable String uid){
        return adminImpl.getPendingVisits(uid);
    }
}
