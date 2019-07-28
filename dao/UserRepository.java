package com.matija.infobip.dao;

import org.springframework.data.repository.CrudRepository;

import com.matija.infobip.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
