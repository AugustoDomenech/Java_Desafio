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

import com.mirante.apirest.model.Client;
import com.mirante.apirest.model.Fone;
import com.mirante.apirest.repository.ClientRepository;

@Controller
@RequestMapping(value="/client")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;
	
	//Salva o clientes
	@CrossOrigin(origins = "http://localhost:3000")	
	@RequestMapping( value = "/save" , method = RequestMethod.POST)
	@ResponseBody
	public Client save( @RequestBody Client client  ) {				
		client.setRegisterDate(LocalDate.now());				
		return clientRepository.save(client);
	}
	
	// busca todos os clientes
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/clients" , method = RequestMethod.GET)
	@ResponseBody
	public List<Client> getClientAll() {									
		return clientRepository.findAll();
	}
	
	//Busca um cliente por ID
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/{id}" , method = RequestMethod.GET)
	@ResponseBody
	public Client getClientbyId( @PathVariable("id") Long id ) {									
		return clientRepository.findById(id).get();
	}
	
	//Remove um cliente por ID
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/{id}" , method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteClient( @PathVariable("id") Long id ) {									
		clientRepository.deleteById(id);
	}	
		
}
