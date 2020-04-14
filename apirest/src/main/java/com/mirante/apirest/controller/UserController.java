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
import com.mirante.service.serviceImpl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	
	public Boolean isAdmin(Long id) {
		try {
			System.out.println("-----");
			// Buscamos o usuário no banco de dados
			Optional<User> userLogin = userRepository.findById(id);
			
			System.out.println("-----");
			
			// Fazemos uma declaração do usuário logado
			User userLogged = null;
			
			//Verificamos se foi achado algum usuário no banco de dados
			if (userLogin.isPresent()) {
				userLogged = userLogin.get();
			} else {
				return null;				
			}
				
			System.out.println(userLogged.getLogin());
			
			if((userLogin != null) && userLogged.getType().getId() == 3 ) {	
				return true;							
			} else {
				return false;
			}
	
		}catch ( Exception e ) {
			System.out.println("Erro ao validar administrador Error :" + e);
			return false;	
		}		
	}
	
	
	
	
	
	//Busca todos os usuários
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/users", method = RequestMethod.GET  )
	@ResponseBody
	public List<User> getUserAll( @RequestHeader("Bearer") String token ) {
		if (UserAuthenticationService.validateToken(token)) {
			UserServiceImpl usip = new UserServiceImpl();
									
			// Verica se o usuário é administrador
			if(isAdmin(UserAuthenticationService.getIdBodyToken(token)) ) {
				return userRepository.findAll();						
			}
		}
		return null;
	};			
	
	//Busca um usuário por um ID especifico
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/{id}", method = RequestMethod.GET  )
	@ResponseBody
	public User getUserId( @RequestHeader("Bearer") String token , @PathVariable("id") Long id ) {
		if (UserAuthenticationService.validateToken(token)) {									
			// Verica se o usuário é administrador
			if( isAdmin(UserAuthenticationService.getIdBodyToken(token)) ) {
				Optional<User> user = userRepository.findById(id);
				
				if (user.isPresent()) {
					return user.get();
				}
			}
		}
		return null;
	};			
	
	//Salva ou edita um novo usuário
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public User saveUser( @RequestHeader("Bearer") String token , @RequestBody User user ) {		
		if (UserAuthenticationService.validateToken(token)) {
			// Verica se o usuário é administrador
			if( isAdmin(UserAuthenticationService.getIdBodyToken(token)) ) {
				user.setRegister_date(LocalDate.now());	
				
				if (user.getId() > 0) {
					user.setLogin( userRepository.findById(user.getId()).get().getLogin() );
				}
				
				return userRepository.save(user);							
			}
		}
		return null;
	};
			
	// Autentica o usuário pelo login e senha
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
	public void userByLogin( @RequestHeader("Bearer") String token, @PathVariable("id") Long id  ) {				
		if (UserAuthenticationService.validateToken(token)) {
			// Verica se o usuário é administrador
			if( isAdmin(UserAuthenticationService.getIdBodyToken(token)) ) {
				userRepository.deleteById(id);
			}		
		}		
	};
}
