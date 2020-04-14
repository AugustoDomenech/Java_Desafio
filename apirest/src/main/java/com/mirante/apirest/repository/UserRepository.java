package com.mirante.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import com.mirante.apirest.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

	//Declaramos uma Query para que possamos buscar pelo login e password
	@Query("SELECT user from User as user where user.login = ?1 and user.password = ?2")
	User findByLogin(String login, String password);

	//alteramos apenas o token no banco de dados
	@Query("UPDATE User as user SET user.token = ?1  where user.id = ?2")
	User setToken(String token, Long id);
}
