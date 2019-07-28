package com.matija.infobip.helper;

import org.springframework.util.StringUtils;

import com.matija.infobip.exc.InvalidURLException;

public class HashGenerator {
	
	private static final int RADIX = 36;
	private static final String PIPE = "-";


	public String shorten(String url) {
		return encode(url);
	}

	private String encode(String url) {
		if (StringUtils.isEmpty(url)) {
			throw new InvalidURLException("Supplied invalid url: empty");
		}

		String hexValue = Integer.toString(url.hashCode(), RADIX);
		if (hexValue.startsWith(PIPE)) {
			hexValue = hexValue.substring(1);
		}
		    return hexValue;
		}

}

