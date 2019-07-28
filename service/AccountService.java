package com.matija.infobip.service;

import java.util.List;
import java.util.Optional;

import com.matija.infobip.entity.Account;

public interface AccountService {
	
	public List<Account> getAllAccounts();
	
	public Optional<Account> getAccount(String accountId);
	
	public Account addAccount(Account account);
	
	public void updateAccount(Account account, String accountId);

	public void deleteAccount(String accountId);
	

}