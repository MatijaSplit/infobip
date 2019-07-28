package com.matija.infobip.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.matija.infobip.entity.Link;

public interface LinkService {
	
	public List<Link> getAllLinks(String accountId);
	
	public Optional<Link> getLink(String url);//find
	
	public Link findByHash(String hash);

	public Link addLink(Link Links); //create
	
	public void updateLink(Link link);

	public void deleteLink(String url);
	
	public String findUrlByHash(String hash); //from repository

	public void deleteByHash(String hash); //from repository

	public Link create(String url,String accountId);

	public HashMap<String, Integer> createMap(List<Link> links);

	public HashMap<String, String> createHashMap(Link savedLink);

	
}