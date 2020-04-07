package com.mirante.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirante.apirest.model.User;

public interface UserRepository  extends JpaRepository<User, Long> {

}
