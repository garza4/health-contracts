package service;
import java.util.ArrayList;

import org.stellar.sdk.KeyPair;
import org.stellar.sdk.Server;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.AccountResponse.Balance;

import model.BalanceList;
import model.StellarAccount;


public class AccountImpl implements Account{
	
	private final Server server = new Server("https://horizon-testnet.stellar.org");


	@Override
	public StellarAccount createAccount() {
		KeyPair pair = KeyPair.random();
		StellarAccount stellAcct = new StellarAccount(
					new String(pair.getSecretSeed()),
					new String(pair.getAccountId()),
					"Keep these values in a secure location. They will not be saved anywhere");
		return stellAcct;
	}
	
	@Override
	public BalanceList checkBalance(StellarAccount stellarAcct) {
		BalanceList balList = new BalanceList();
		try {
			AccountResponse acct = server.accounts().account(stellarAcct.getAccountId());

			balList.setBalances(new ArrayList<Balance>());
			for(AccountResponse.Balance balance : acct.getBalances()) {
				balList.getBalances().add(balance);
			}
		}catch(Exception e) {
			System.out.println("there was an issue");
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
