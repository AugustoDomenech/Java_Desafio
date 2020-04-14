package com.mirante.apirest.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mirante.apirest.model.User;
import com.mirante.apirest.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	//Busca todos os usuários
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/users", method = RequestMethod.GET  )
	@ResponseBody
	public List<User> getUserAll( ) {
		return userRepository.findAll();
	}			
	
	//Busca um usuário por um ID especifico
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/{id}", method = RequestMethod.GET  )
	@ResponseBody
	public User getUserId( @PathVariable("id") Long id ) {
		return userRepository.findById(id).get();
	}				
	
	//Salva um novo usuário ou edita o atual
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public User saveUser( @RequestBody User user ) {		
		user.setRegister_date(LocalDate.now());
		return userRepository.save(user);
	}
		
	// Verifica se o usuário existe pelo seu login e senha
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/authLogin/{email}/{password}", method = RequestMethod.GET)
	@ResponseBody
	public User userByLogin( @PathVariable("email") String email, @PathVariable("password") String password  ) {		
		
		User user = userRepository.findByLogin(email, password);
		if (user.getId() > -1L)  {
		  UserAuthenticationService.authenticate(user);
		  user = userRepository.save(user);
		}
		return user;
	}	
	
	// Remove o usuario pelo ID
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/removeUser/{id}", method = RequestMethod.DELETE)
	public void userByLogin( @PathVariable("id") Long id  ) {				
		userRepository.deleteById(id);
	}		
}
