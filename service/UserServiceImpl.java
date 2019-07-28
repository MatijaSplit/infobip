package com.matija.infobip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matija.infobip.dao.UserRepository;
import com.matija.infobip.entity.User;

@Service
public class UserServiceImpl  implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User addUser(User user) {
		
		userRepository.save(user);
		
		return user;
	}



	

}
