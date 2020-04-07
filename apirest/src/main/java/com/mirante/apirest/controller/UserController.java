package com.mirante.apirest.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping( value = "/", method = RequestMethod.GET  )
	@ResponseBody
	public List<User> getUserAll( ) {
		return userRepository.findAll();
	}			
	
	@RequestMapping( value = "/{id}", method = RequestMethod.GET  )
	@ResponseBody
	public User getUserId( @PathVariable("id") Long id ) {
		return userRepository.findById(id).get();
	}				
	
	@RequestMapping( value = "/save")
	@ResponseBody
	public User saveUser( @RequestBody User user ) {		
		user.setRegister_date(LocalDate.now());
		return userRepository.save(user);
	}
	
}
