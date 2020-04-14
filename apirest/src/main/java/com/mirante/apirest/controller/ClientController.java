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

import com.mirante.apirest.model.Client;
import com.mirante.apirest.model.Fone;
import com.mirante.apirest.model.User;
import com.mirante.apirest.repository.ClientRepository;
import com.mirante.apirest.repository.UserRepository;

@Controller
@RequestMapping(value="/client")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;

	@Autowired	
	UserRepository userRepository;
	
	
	public Boolean isManager(Long id) {
		try {
			// Buscamos o usuário no banco de dados
			Optional<User> userLogin = userRepository.findById(id);
				
			// Fazemos uma declaração do usuário logado
			User userLogged = null;
			
			//Verificamos se foi achado algum usuário no banco de dados
			if (userLogin.isPresent()) {
				userLogged = userLogin.get();
			} else {
				return null;				
			}
				
			System.out.println(userLogged.getLogin());
			
			if((userLogin != null) && userLogged.getType().getId() == 4 ) {	
				return true;							
			} else {
				return false;
			}
	
		}catch ( Exception e ) {
			System.out.println("Erro ao validar gerente Error :" + e);
			return false;	
		}		
	}
	
	public Boolean isAnalyst(Long id) {
		try {
			// Buscamos o usuário no banco de dados
			Optional<User> userLogin = userRepository.findById(id);
				
			// Fazemos uma declaração do usuário logado
			User userLogged = null;
			
			//Verificamos se foi achado algum usuário no banco de dados
			if (userLogin.isPresent()) {
				userLogged = userLogin.get();
			} else {
				return null;				
			}
				
			System.out.println(userLogged.getLogin());
			
			if((userLogin != null) && userLogged.getType().getId() == 5 ) {	
				return true;							
			} else {
				return false;
			}
	
		}catch ( Exception e ) {
			System.out.println("Erro ao validar gerente Error :" + e);
			return false;	
		}		
	}	

	
	
	//Salva o clientes
	@CrossOrigin(origins = "http://localhost:3000")	
	@RequestMapping( value = "/save" , method = RequestMethod.POST)
	@ResponseBody
	public Client save(@RequestHeader("Bearer") String token , @RequestBody Client client  ) {				
		if (UserAuthenticationService.validateToken(token)) {	
		
		// Verica se o usuário é um gerente
		if( isManager(UserAuthenticationService.getIdBodyToken(token)) ) {
		
			if	(client.getId() > 0) {
				// Caso esteja editando a pessoa é mantida as seguintes informações
				Client clientNoDataChange = clientRepository.findById(client.getId()).get();
				client.setDocument(clientNoDataChange.getDocument());
				client.setRegisterDate(clientNoDataChange.getRegisterDate());
				client.setUserRegister(clientNoDataChange.getUserRegister());
			};		
			
			client.setRegisterDate(LocalDate.now());				
						
			return clientRepository.save(client);
			
		} else {
			System.out.println("Somente gerente pode realizar o cadastro de pessoas.");
			return null;
		}
		
		}
	
		return null;
	}
	
	// busca todos os clientes exeto o nome dos pais e telefone
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( value = "/clients" , method = RequestMethod.GET)
	@ResponseBody
	public List<Client> getClientAll(@RequestHeader("Bearer") String token) {						
		if (UserAuthenticationService.validateToken(token)) {	
		
		// Verica se o usuário é um gerente ou analistas
		if( isManager(UserAuthenticationService.getIdBodyToken(token)) || isAnalyst(UserAuthenticationService.getIdBodyToken(token)) ) {
			return clientRepository.findAll();
		}
		
		}
										
		System.out.println("Somente gerente ou analista pode realizar a listagem das pessoas.");
		return null;
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
