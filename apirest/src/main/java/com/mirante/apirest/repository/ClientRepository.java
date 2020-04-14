package com.mirante.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mirante.apirest.model.Client;
import com.mirante.apirest.model.User;

public interface ClientRepository extends JpaRepository<Client, Long>{	
}
