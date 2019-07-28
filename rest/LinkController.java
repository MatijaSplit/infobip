package com.matija.infobip.rest;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matija.infobip.entity.Account;
import com.matija.infobip.entity.Link;
import com.matija.infobip.service.LinkService;

@RestController
//@RequestMapping("/")
public class LinkController {
	
	@Autowired
	private LinkService linkService;

	
	@RequestMapping("/statistics/{accountId}")
	public ResponseEntity<?> mapReturn(@PathVariable String accountId){
		
		List<Link> links=linkService.getAllLinks(accountId);
		HashMap<String,Integer> map=linkService.createMap(links);		
		return ResponseEntity.ok(map);
	}
	
	@RequestMapping("/accounts/{accountId}/links/{url}")
	public Optional<Link> getLink(@PathVariable String url) {
		return linkService.getLink(url);
	}
	
	//POST links. 1.2
	@RequestMapping(method=POST, value="/accounts/{accountId}/links")
	public Link addLink(@RequestBody Link link, @PathVariable String accountId) {
		link.setAccount(new Account(accountId,"","",true));
		return linkService.addLink(link);
	}
	
	@RequestMapping(method = POST, value="/register/{accountId}")
	public ResponseEntity<?> createShortLink(@RequestBody Link link, @PathVariable String accountId) {
		link.setAccount(new Account(accountId,"","",true));
		Link savedLink = linkService.create(link.getUrl(),accountId);
		HashMap<String,String> map=linkService.createHashMap(savedLink);
		return ResponseEntity.ok().body(map);
	}

	
	
	@RequestMapping(method=PUT, value="/accounts/{accountId}/links/{url}")
	public void updateLink(@RequestBody Link link,@PathVariable String accountId,@PathVariable String url) {
		link.setAccount(new Account(accountId,"","",true));
		linkService.updateLink(link);
	}
	
	@RequestMapping(method=DELETE, value="/accounts/{accountId}/links/{url}")
	public void deleteAccount(@PathVariable String url) {
		linkService.deleteLink(url);
	}
}
