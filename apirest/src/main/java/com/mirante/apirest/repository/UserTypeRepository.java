package com.mirante.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirante.apirest.model.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {

}
