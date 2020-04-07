package com.mirante.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirante.apirest.model.Client;
import com.mirante.apirest.repository.ClientRepository;
import com.mirante.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	
	@Override
	public List<Client> findAll() {		
		return clientRepository.findAll() ;
	}

	@Override
	public Client findById(long id) {
		return clientRepository.findById(id).get() ;
	}

	@Override
	public Client save(Client client) {
		return clientRepository.save(client) ;
	}

}
