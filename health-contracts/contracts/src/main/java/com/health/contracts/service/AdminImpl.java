package com.health.contracts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.health.contracts.entity.VisitationEntity;
import com.health.contracts.model.LogVisitationReq;
import com.health.contracts.repository.AdminRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminImpl implements Admin{
	private AdminRepository adminRepo;
	
	@Autowired
	public AdminImpl(AdminRepository adminRepo) {
		this.adminRepo=adminRepo;
	}

	@Override
	public void logVisitation(LogVisitationReq req) {
		try {
			VisitationEntity visit = VisitationEntity.builder()
					.visitRef(null)
					.comments(req.getComments())
					.service(req.getService())
					.requestedFunds(req.getReqForFundsUSD())
					.uid(req.getUid()).build();
			adminRepo.save(visit);
		}catch(Exception e) {
			log.error("could not log visitation for user");
		}
	}
	
}
