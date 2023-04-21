package service;

import model.StellarAccount;

public interface Account {
	public StellarAccount createAccount();
	
	public String resetAccount();
	
	public String fundAccount();
}
