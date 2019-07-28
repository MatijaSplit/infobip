package com.matija.infobip.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
public class Account {

	@Id
	private String accountId;
	
	private boolean success;
	
	private String description;
	
	private String password;
	
	public Account() {
		
	}
		
	public Account(String accountId, String description, String password,boolean success) {
		super();
		this.accountId = accountId;
		this.success = success;
		this.description = description;
		this.password = password;
		//this.links = links;
	}



	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [accountId=" + accountId + ", success=" + success + ", description=" + description + ", password="
				+ password + "]";
	}

	
	
	
	

}
