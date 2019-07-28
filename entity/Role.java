package com.matija.infobip.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String username;
	
	private String authority;
	
	public Role() {
		
	}

	public Role(String username, String authority) {
		super();
		this.username = username;
		this.authority = authority;
	}


	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	

}
