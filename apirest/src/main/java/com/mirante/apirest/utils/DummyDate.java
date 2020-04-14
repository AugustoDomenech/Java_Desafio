package com.mirante.apirest.utils;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mirante.apirest.model.ClientType;
import com.mirante.apirest.model.FoneType;
import com.mirante.apirest.model.User;
import com.mirante.apirest.model.UserType;
import com.mirante.apirest.repository.ClientTypeRepository;
import com.mirante.apirest.repository.FoneTypeRepository;
import com.mirante.apirest.repository.UserRepository;
import com.mirante.apirest.repository.UserTypeRepository;
import com.mirante.service.TokenService;

@Component
public class DummyDate {

	
	@Autowired
	ClientTypeRepository clientTypeRepository;
	
	@Autowired
	UserTypeRepository userTypeRepository;	
	
	@Autowired
	FoneTypeRepository foneTypeRepository;	
	
	@Autowired
	UserRepository userRepository;	
	
	
	
	//@PostConstruct
	public void save() {
		// Iniciamos alguns valores no banco de dados
					
		//Insere os tipos de Cliente Default
		
		ClientType clientType = new ClientType();
		clientType.setName("Física");
		clientTypeRepository.save(clientType); 
		
		System.out.println(clientType.toString() );
		
		clientType = new ClientType();
		clientType.setName("Jurídica");
		clientTypeRepository.save(clientType);
		
		System.out.println(clientType.toString() );
		
		// Insere os tipos de usuário Default
		UserType userType = new UserType();
		userType.setId(1L);
		userType.setName("Administrador");
		userTypeRepository.save(userType);
		
		System.out.println(userType.toString() );
		
		userType = new UserType();
		userType.setName("Gerente");
		userTypeRepository.save(userType);		
		
		System.out.println(userType.toString() );
		
		userType = new UserType();
		userType.setName("Analista");
		userTypeRepository.save(userType);		
				
		System.out.println(userType.toString() );
				
		// Insere os tipos de contato default
		FoneType foneType = new FoneType();
		foneType.setName("Residencial");
		foneTypeRepository.save(foneType);				
		
		System.out.println(foneType.toString() );
		
		foneType = new FoneType();
		foneType.setName("Celular");
		foneTypeRepository.save(foneType);
		
		System.out.println(foneType.toString() );
		
		foneType = new FoneType();
		foneType.setName("Trabalho");
		foneTypeRepository.save(foneType);
		
		System.out.println(foneType.toString() );
		
		
		UserType usertype = new UserType(); 
		usertype.setId(1L);
		
		User user = new User();
		user.setLogin("admin");
		user.setName("admin");
		user.setPassword("admin");
		user.setRegister_date(LocalDate.now());
		user.setType(usertype);
		TokenService.generatetoken(user);
		userRepository.save(user);
		
	
}
	
	
}
