package com.mirante.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mirante.apirest.model.UserType;
import com.mirante.apirest.repository.UserTypeRepository;

@Controller
@RequestMapping("/usertype/")
public class UserTypeController {
	
	@Autowired
	UserTypeRepository userTypeRepository;
	
	// busca por todos os tipo 
	@CrossOrigin(origins = "http://localhost:3000")	
	@RequestMapping(value = "", method =  RequestMethod.GET)
	@ResponseBody 
	public List<UserType> getAllUserType() {
		return userTypeRepository.findAll();
		
	}

	// busca por um tipo 
	@CrossOrigin(origins = "http://localhost:3000")	
	@RequestMapping(value = "{id}", method =  RequestMethod.POST)
	@ResponseBody 
	public UserType getUserById(@PathVariable( "id") long id ) {
		return userTypeRepository.findById(id).get();
		
	}
		
	
}
