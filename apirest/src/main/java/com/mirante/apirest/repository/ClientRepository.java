package com.mirante.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirante.apirest.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
