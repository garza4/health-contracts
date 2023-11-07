package com.health.contracts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.health.contracts.entity.HealthUser;
import com.health.contracts.model.SaveUserReq;
import com.health.contracts.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
@Slf4j
public class UserImpl implements Users{
	private UserRepository userRepo;
//	private IAuthenticationFacade userContext;
        private PasswordEncoder passwordEncoder;
        @Autowired
        public UserImpl(UserRepository userRepo,PasswordEncoder passwordEncoder){
            this.userRepo=userRepo;
            this.passwordEncoder=passwordEncoder;
        }

	@Override
	public HealthUser getUsers(String uid) {
            log.info("user is: " +  uid);
            HealthUser user = null;
            try{
                user = userRepo.getUserByUName(uid);
            }catch(Exception e){
                log.error("fetching user did not work",e);
            }
            return user;
	}

	@Override
	public void saveUser(SaveUserReq user) {
		try {
                    HealthUser hUser = HealthUser.builder()
                        .upkid(null)
                        .provider(user.getProvider())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .role(user.getRole())
                        .uName(user.getUName())
                        .password(passwordEncoder.encode(user.getPassword()))
                        .build();
			userRepo.save(hUser);
		}catch(Exception e) {
			log.error("could not save user",e);
		}	
	}

}
