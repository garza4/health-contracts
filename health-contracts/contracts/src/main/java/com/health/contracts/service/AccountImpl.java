package com.health.contracts.service;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.stellar.sdk.KeyPair;
import org.stellar.sdk.Server;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.AccountResponse.Balance;

import lombok.extern.slf4j.Slf4j;
import com.health.contracts.model.BalanceList;
import com.health.contracts.model.StellarAccount;
import com.health.contracts.security.IAuthenticationFacade;

@Slf4j // or: @Log @CommonsLog @Log4j @Log4j2 @XSlf4j
@Component
public class AccountImpl implements Account {
	private String stellarServer = "https://horizon-testnet.stellar.org";
	private final Server server = new Server(stellarServer);
	private IAuthenticationFacade userContext;
	
	public AccountImpl(IAuthenticationFacade userContext) {
		this.userContext=userContext;
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
	public BalanceList checkBalance(String stellarAcct) {
		BalanceList balanceList = new BalanceList();
		Boolean hasBalance = false;
		log.info("checking account for {}",stellarAcct);
		try {
			AccountResponse acct = server.accounts().account(stellarAcct);
			if(acct.getBalances().length ==0) {
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

}
