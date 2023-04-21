package service;
import org.stellar.sdk.KeyPair;

import model.StellarAccount;


public class AccountImpl implements Account{

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
