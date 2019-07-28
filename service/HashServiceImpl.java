package com.matija.infobip.service;
	

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.matija.infobip.exc.InvalidURLException;
import com.matija.infobip.helper.SupportedProtocol;

@Service
public class HashServiceImpl implements HashService{
	
	private static final int RADIX = 36;
	private static final String PIPE = "-";

	@Override
	public String shorten(String url) {
	    return encode(url);
	}

	private String encode(String url) {
		if (StringUtils.isEmpty(url)) {
			throw new InvalidURLException("Supplied invalid url: empty");
	    }
		boolean isSupportedProtocol = SupportedProtocol.contains(url);
		if (!isSupportedProtocol) {
			throw new InvalidURLException("URL protocol not supported");
	    }

	    String hexValue = Integer.toString(url.hashCode(), RADIX);
	    if (hexValue.startsWith(PIPE)) {
	      hexValue = hexValue.substring(1);
	    }
	    return hexValue;
	}

}

