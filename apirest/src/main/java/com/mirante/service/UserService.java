package com.mirante.service;

import java.util.List;

import com.mirante.apirest.model.User;

public interface UserService {
	List<User> findAll();
	User findById(long id);
	User save(User user);
	User findByLogin(String name, String password);
	User findId(String name);
}
