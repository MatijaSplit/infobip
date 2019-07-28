package com.matija.infobip.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.matija.infobip.entity.Account;


public interface AccountRepository extends CrudRepository<Account, String> {

	
	public Account findByAccountId(String accountId);

}
