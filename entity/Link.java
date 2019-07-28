package com.matija.infobip.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Link{
	
	@Id
	private String url;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Account account;
	
	private int counter;
	
	private String hash;
	
	public Link() {
		
	}
	
	public Link(String url, String accountId, int counter, String hash) {
		super();
		this.url = url;
		this.account = new Account(accountId,"","",true);
		this.counter = counter;
		this.hash = hash;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
	



	@Override
	public int hashCode() {
		return Objects.hash(url, hash);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final Link other = (Link) obj;
		return Objects.equals(this.url, other.url) && Objects.equals(this.hash, other.hash);
	}

	@Override
	public String toString() {
		return "Links [, url=" + url + ", hash=" + hash + "]";
	}
}

	
