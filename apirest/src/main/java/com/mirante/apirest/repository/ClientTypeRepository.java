package com.mirante.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirante.apirest.model.ClientType;

public interface ClientTypeRepository extends JpaRepository<ClientType, Long> {

}
