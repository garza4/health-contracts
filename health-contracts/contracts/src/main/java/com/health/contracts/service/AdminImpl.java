package com.health.contracts.service;

import com.health.contracts.entity.HealthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.health.contracts.entity.VisitationEntity;
import com.health.contracts.model.LogVisitationReq;
import com.health.contracts.repository.AdminRepository;
import com.health.contracts.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminImpl implements Admin{
	private AdminRepository adminRepo;
        private UserImpl userImpl;
	
	@Autowired
	public AdminImpl(AdminRepository adminRepo,UserImpl userImpl) {
            this.adminRepo=adminRepo;
            this.userImpl=userImpl;
	}

	@Override
	public void logVisitation(LogVisitationReq req) {
            HealthUser user = null;
            try {
                log.info("add visitaiton log for: " + req.getUid());
                user = userImpl.getUsers(req.getUid());
                VisitationEntity visit = VisitationEntity.builder()
                    .visitRef(null)
                    .comments(req.getComments())
                    .service(req.getService())
                    .requestedFunds(req.getReqForFundsUSD())
                    .uid(req.getUid())
                    .upkid(user.getUpkid()).build();
                adminRepo.save(visit);
            }catch(Exception e) {
                log.info("user is: " + user.toString());   
                log.error("could not log visitation for user",e);
            }
	}
	
}
