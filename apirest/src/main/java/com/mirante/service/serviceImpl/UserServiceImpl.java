package com.mirante.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.mirante.apirest.model.User;
import com.mirante.apirest.repository.UserRepository;
import com.mirante.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAll() {		
		return userRepository.findAll();
	}

	@Override
	public User findById(long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}


	@Override
	public User findByLogin(String name, String password) {
		return userRepository.findByLogin(name, password);
	}

	@Override
	public User findId(String name) {
		return null;
	}

}
