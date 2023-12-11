package com.health.contracts.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.health.contracts.entity.HealthUser;
import com.health.contracts.entity.ProviderEntity;
import com.health.contracts.model.*;
import com.health.contracts.repository.ProviderRepository;
import com.health.contracts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.stellar.sdk.KeyPair;
import org.stellar.sdk.Server;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.AccountResponse.Balance;

import lombok.extern.slf4j.Slf4j;
//import com.health.contracts.security.IAuthenticationFacade;

@Slf4j // or: @Log @CommonsLog @Log4j @Log4j2 @XSlf4j
@Component
public class AccountImpl implements Account {
	private String stellarServer = "https://horizon-testnet.stellar.org";
	private final Server server = new Server(stellarServer);

	private ProviderRepository providerRepo;

	private UserDetailsService userDetailsService;

	private UserRepository userRepository;

	@Autowired
	public AccountImpl(ProviderRepository providerRepo,UserDetailsService userDetailsService,UserRepository userRepository){
		this.providerRepo = providerRepo;
		this.userDetailsService = userDetailsService;
		this.userRepository = userRepository;
	}

	@Override
	public StellarAccount createAccount() {
		StellarAccount stellAcct = null;
		try {
			KeyPair pair = KeyPair.random();
			stellAcct = new StellarAccount(new String(pair.getSecretSeed()), new String(pair.getAccountId()),
					"Keep these values in a secure location. They will not be saved on this system");
		} catch (Exception e) {
			log.error("Oof could not create an account", e);
		}
		return stellAcct;

	}

	@Override
	public BalanceList checkBalance() {
		BalanceList balanceList = new BalanceList();
		Boolean hasBalance = false;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		HealthUser user = (HealthUser) auth.getPrincipal();
		HealthUserDetails hUser = (HealthUserDetails) userDetailsService.loadUserByUsername(user.getUName());

		String provider = hUser.getUser().getProvider();
//		log.info("checking account for {}",userContext.getUserContext().getUser().getProvider());
		try {
			ProviderEntity account = providerRepo.getAccountId(provider);
			AccountResponse acct = server.accounts().account(account.getAcctId());
			if(acct.getBalances().length == 0) {
				log.info("no balances");
			}else hasBalance=true;
			if(hasBalance) {
				balanceList.setBalances(new ArrayList<Balance>());
				for (AccountResponse.Balance balance : acct.getBalances()) {
					balanceList.getBalances().add(balance);
				}
			}
			
		} catch (Exception e) {
			log.error("there was an issue checking the user's balance", e);
		}
		return balanceList;
	}

	@Override
	public String resetAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fundAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddUserToSystemResp addUserToSystem(AddUserToSystemReq req){
		List<HealthUser> newUser = null;
		AddUserToSystemResp resp = new AddUserToSystemResp();
		StellarAccount newUserAcct;
		try{
			newUser = userRepository.getUserByName(req.getUserName());
			if(newUser.size() == 0){
				newUserAcct = createAccount();
				userRepository.save(HealthUser.builder()
						.uName(req.getUserName())
						.firstName(req.getFirstName())
						.lastName(req.getLastName())
						.role("admin")
						.provider(req.getOrgName())
						.upkid(null).build());
				resp.setStatus("S");
				resp.setStellarAccount(newUserAcct);
			}else{
				resp.setStatus("Failed - User already exists in the system");
				return resp;
			}
		}catch (Exception e){
			log.error("Could not add user to system",e);
			resp.setStatus("Failed due to system error");
		}
		return resp;
	}

}
