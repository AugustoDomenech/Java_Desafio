package com.mirante.apirest.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public List<User> getUserAll( @RequestHeader("Bearer") String token ) {
		if (UserAuthenticationService.validateToken(token)) {
			return userRepository.findAll();	
		}
		return null;
	}			
	
	//Busca um usuário por um ID especifico
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/{id}", method = RequestMethod.GET  )
	@ResponseBody
	public User getUserId( @RequestHeader("Bearer") String token , @PathVariable("id") Long id ) {
		if (UserAuthenticationService.validateToken(token)) {
			return userRepository.findById(id).get();	
		}
		return null;
	}				
	
	//Salva um novo usuário ou edita o atual
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public User saveUser( @RequestHeader("Bearer") String token , @RequestBody User user ) {		
		if (UserAuthenticationService.validateToken(token)) {
			Optional<User> userLogin = userRepository.findById( UserAuthenticationService.getIdBodyToken(token));
			
			User userLogged = null;
			if (userLogin.isPresent()) {
				userLogged = userLogin.get();
			} else {
				return null;				
			}
							
			if((userLogin != null) && userLogged.getType().getId() == 3 ) {
				user.setRegister_date(LocalDate.now());	
				return userRepository.save(user);							
			}
		}
		return null;
	};
	
		
	// Verifica se o usuário existe pelo seu login e senha
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/authLogin/{email}/{password}", method = RequestMethod.GET)
	@ResponseBody
	public User userByLogin( @PathVariable("email") String email, @PathVariable("password") String password  ) {		
		User user = userRepository.findByLogin(email, password);
		UserAuthenticationService.authenticate(user);
		userRepository.save(user);
		return user;
	}	
	
	// Remove o usuario pelo ID
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/removeUser/{id}", method = RequestMethod.DELETE)
	public void userByLogin( @PathVariable("id") Long id  ) {				
		userRepository.deleteById(id);
	}		
}
