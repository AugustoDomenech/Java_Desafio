package com.mirante.service;

import java.util.List;

import com.mirante.apirest.model.Client;

public interface ClientService {
	
	List<Client> findAll();
	Client findById(long id);
	Client save(Client client);
	
}
