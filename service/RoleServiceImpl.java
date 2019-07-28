package com.matija.infobip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matija.infobip.dao.RoleRepository;
import com.matija.infobip.entity.Role;

@Service
public class RoleServiceImpl  implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role addRole(Role role) {		
		roleRepository.save(role);
		return role;
	}

	

}
