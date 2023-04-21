package service;

import model.BalanceList;
import model.StellarAccount;

public interface Account {
	public StellarAccount createAccount();
	
	public String resetAccount();
	
	public String fundAccount();
	
	public BalanceList checkBalance(StellarAccount stellarAcct);
}
