package com.matija.infobip.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.matija.infobip.entity.Link;

public interface LinkRepository extends CrudRepository<Link, String> {
	
	public List<Link> findByAccountAccountId(String accountId);
	
	public Link findByHash(String hash);
	
	public Link findByUrl(String url);
	
	
}
