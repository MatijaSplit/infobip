package com.matija.infobip.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.matija.infobip.entity.Link;
import com.matija.infobip.service.LinkService;

@Controller
@RequestMapping("/")
public class RedirectController {
	private LinkService linkService;

	@Autowired
	public RedirectController(LinkService linkService) {
		this.linkService = linkService;
	}

	@RequestMapping(value = "hash/{hash}", method = RequestMethod.GET)
	public String redirect(@PathVariable("hash") String hash) {
		Link tempLink=linkService.findByHash(hash);
		if(tempLink!=null) {
			tempLink.setCounter(tempLink.getCounter()+1);
			linkService.updateLink(tempLink);
		}
		
		
		return "redirect:" + linkService.findUrlByHash(hash);
	}
	
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String showHelp() {
		
		return "help";
	}
}