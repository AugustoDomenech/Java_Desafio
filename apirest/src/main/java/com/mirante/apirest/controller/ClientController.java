package com.mirante.apirest.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mirante.apirest.model.Client;
import com.mirante.apirest.model.Fone;
import com.mirante.apirest.repository.ClientRepository;

@Controller
@RequestMapping(value="/client")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;
	
	@RequestMapping( value = "/save" , method = RequestMethod.POST)
	@ResponseBody
	public Client save( @RequestBody Client client  ) {				
		client.setRegisterDate(LocalDate.now());
		
		for( Fone fone : client.getFones() ) {
			fone.setClient(client);
		}
		
		return clientRepository.save(client);
	}
	
	@RequestMapping( value = "/clients" , method = RequestMethod.GET)
	@ResponseBody
	public List<Client> getClientAll() {									
		return clientRepository.findAll();
	}
	
	@RequestMapping( value = "/{id}" , method = RequestMethod.GET)
	@ResponseBody
	public Client getClientbyId( @PathVariable("id") Long id ) {									
		return clientRepository.findById(id).get();
	}
		
	
	@RequestMapping( value = "/save" , method = RequestMethod.PUT)
	@ResponseBody
	public Client editClient( @RequestBody Client client  ) {				
		client.setRegisterDate(LocalDate.now());
		
		return clientRepository.save(client);
	}
	
}
