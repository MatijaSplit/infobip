package com.matija.infobip.helper;

import org.apache.commons.text.RandomStringGenerator;

public class PasswordGenerator {
	
	char [][] pairs = {{'a','z'},{'0','9'},{'A','Z'}};
	public String generateRandomSpecialCharacters(int length) {
	    RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(pairs)
	        .build();
	    return pwdGenerator.generate(length);
	}

}
