package com.matija.infobip.rest;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matija.infobip.entity.Account;
import com.matija.infobip.service.AccountService;

@RestController
@RequestMapping("/")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@RequestMapping("/accounts")
	public List<Account> getAllAccounts(){
		return accountService.getAllAccounts();
	}
	
	@RequestMapping("/accounts/{accountId}")
	public Optional<Account> getAccount(@PathVariable String accountId) {
		return accountService.getAccount(accountId);
	}
	
	@RequestMapping(method=POST, value="/account")
	public Account addAccount(@RequestBody Account account) {
		return accountService.addAccount(account);
	}
	
	@RequestMapping(method=PUT, value="/accounts/{accountId}")
	public void updateAccount(@RequestBody Account account,@PathVariable String accountId) {
		accountService.updateAccount(account,accountId);
	}
	
	@RequestMapping(method=DELETE, value="/accounts/{accountId}")
	public void deleteAccount(@PathVariable String accountId) {
		accountService.deleteAccount(accountId);
	}
}
