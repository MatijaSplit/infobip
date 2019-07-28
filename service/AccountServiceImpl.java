package com.matija.infobip.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.matija.infobip.dao.AccountRepository;
import com.matija.infobip.dao.RoleRepository;
import com.matija.infobip.dao.UserRepository;
import com.matija.infobip.entity.Account;
import com.matija.infobip.entity.Role;
import com.matija.infobip.entity.User;
import com.matija.infobip.exc.BadDataException;
import com.matija.infobip.exc.UserNotFoundException;
import com.matija.infobip.helper.PasswordGenerator;



@Service
public class AccountServiceImpl  implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	public List<Account> getAllAccounts(){
		List<Account> accounts=new ArrayList();
		accountRepository.findAll().forEach(accounts::add);
		return accounts;
	}
	
	public Optional<Account> getAccount(String accountId) {
		if(!accountRepository.findById(accountId).isPresent()) {			
			throw new UserNotFoundException("account does not exist");
		}
		return accountRepository.findById(accountId);
	}

	public Account addAccount(Account account) {
		
		boolean test=account.getAccountId().matches("[a-zA-Z0-9]*$");
		if(test==false) {
			throw new BadDataException("Account name can only contain letters and numbers");
		}
		if(accountRepository.findById(account.getAccountId()).isPresent()) {
			account.setDescription("Account ID already taken");			
			return account;
		}
		account.setSuccess(true);
		account.setDescription("Your account is opened");
		PasswordGenerator pass=new PasswordGenerator();
		String password=pass.generateRandomSpecialCharacters(8);
		account.setPassword(password);
		accountRepository.save(account);
		userService.addUser(new User(account.getAccountId(),"{noop}"+password,true) );
		roleService.addRole(new Role(account.getAccountId(),"ROLE_USER"));
		return account;
	}
	
	public void updateAccount(Account account, String accountId) {
		accountRepository.save(account);
	}
	
	public void deleteAccount(String accountId) {
		accountRepository.deleteById(accountId);
	}

}
