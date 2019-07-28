package com.matija.infobip.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matija.infobip.dao.AccountRepository;
import com.matija.infobip.dao.LinkRepository;
import com.matija.infobip.entity.Account;
import com.matija.infobip.entity.Link;
import com.matija.infobip.exc.LinkNotFoundException;
import com.matija.infobip.exc.UserNotFoundException;

@Service
public class LinkServiceImpl  implements LinkService{
	
	@Autowired
	private LinkRepository linkRepository;	
	
	@Autowired
	private HashService shortenService;	
	
	@Autowired
	private AccountRepository accountRepository;
	
	public List<Link> getAllLinks(String accountId){
		
		if(!accountRepository.findById(accountId).isPresent()) {			
			throw new UserNotFoundException("account does not exist");
		}
		
		List<Link> links=new ArrayList<>();
		linkRepository.findByAccountAccountId(accountId).forEach(links::add);
		return links;
	}
	
	public Optional<Link> getLink(String url) {
		return linkRepository.findById(url);
	}
	
	public Link addLink(Link link) {
		linkRepository.save(link);
		return link;
	}
	
	public void updateLink(Link link) {
		linkRepository.save(link);
	}
	
	public void deleteLink(String url) {
		linkRepository.deleteById(url);
	}


	@Override
	public Link create(String url,String accountId) {
		Link resultLink;
		Link existingLink = linkRepository.findByUrl(url);
	    if (existingLink != null) {
	    	existingLink.setCounter(existingLink.getCounter()+1);
	    	resultLink = existingLink;
	    	linkRepository.save(resultLink);

	    } else {
	    	resultLink = createAndSaveLink(url,accountId);    
	    }
	    return resultLink;
	    
	  }
	@Override
	@Transactional
	public void deleteByHash(String hash) {

		Link foundLink = linkRepository.findByHash(hash);
	    if (foundLink == null) {
	    	throw new LinkNotFoundException("No link found for hash: " + hash);
	    }

	    linkRepository.delete(foundLink);
	}

	  @Override
	  public String findUrlByHash(String hash) {
		  String url;
		  Link foundLink = linkRepository.findByHash(hash);
		  if (foundLink == null) {
			  url = "https://net-spin.de";
		  } else {
			  url = foundLink.getUrl();
		  }

		  return url;
	  }

	  private Link createAndSaveLink(String url,String accountId) {
		  String hash = shortenService.shorten(url);

		  Link requestedLink = new Link();
		  requestedLink.setUrl(url);
		  requestedLink.setHash(hash);
		  requestedLink.setCounter(1);
		  requestedLink.setAccount(new Account(accountId,"","",true));
		  Link savedLink = linkRepository.save(requestedLink);
		  return savedLink;
	  }

	@Override
	public HashMap<String, Integer> createMap(List<Link> links) {
		
		HashMap<String, Integer> stats=new HashMap<String,Integer>();
		System.out.println(links);
		for(int i=0;i<links.size();i++) {
			stats.put(links.get(i).getUrl(),links.get(i).getCounter());
		}
		return stats;
	}

	@Override
	public HashMap<String, String> createHashMap(Link savedLink) {
		HashMap<String,String> response=new HashMap<String,String>();
		response.put("shortUrl", savedLink.getHash());
		return response;
	}

	@Override
	public Link findByHash(String hash) {
		return linkRepository.findByHash(hash);
	}

}
