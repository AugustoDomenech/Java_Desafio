package com.mirante.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirante.apirest.model.User;
import com.mirante.apirest.repository.UserRepository;
import com.mirante.service.TokenService;

@Service
public class UserAuthenticationService {
	
	public static String authenticate(User user) {
		String token = TokenService.generatetoken(user);
		user.setToken(token);
		return token;
		
	}
	
	
}
