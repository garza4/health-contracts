package service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.stellar.sdk.KeyPair;
import org.stellar.sdk.Server;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.AccountResponse.Balance;

import lombok.extern.slf4j.Slf4j;
import model.BalanceList;
import model.StellarAccount;;

@Slf4j // or: @Log @CommonsLog @Log4j @Log4j2 @XSlf4j
public class AccountImpl implements Account{
	@Value("${stellar.net}")	
	private String stellarServer;
	private final Server server = new Server(stellarServer);


	@Override
	public StellarAccount createAccount() {
		StellarAccount stellAcct = null;
		try {
		KeyPair pair = KeyPair.random();
		stellAcct = new StellarAccount(
					new String(pair.getSecretSeed()),
					new String(pair.getAccountId()),
					"Keep these values in a secure location. They will not be saved on this system");
		}catch(Exception e) {
			log.error("Oof could not create an account",e);
		}
		return stellAcct;	

	}
	
	@Override
	public BalanceList checkBalance(String stellarAcct) {
		BalanceList balList = new BalanceList();
		try {
			AccountResponse acct = server.accounts().account(stellarAcct);

			balList.setBalances(new ArrayList<Balance>());
			for(AccountResponse.Balance balance : acct.getBalances()) {
				balList.getBalances().add(balance);
			}
		}catch(Exception e) {
			log.error("there was an issue checking the user's balance",e);
		}
		return balList;
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
