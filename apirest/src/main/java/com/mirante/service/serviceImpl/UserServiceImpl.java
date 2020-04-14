package com.mirante.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirante.apirest.model.User;
import com.mirante.apirest.repository.UserRepository;
import com.mirante.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAll() {		
		return userRepository.findAll();
	}

	@Override
	public User findById(long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}


	@Override
	public User findByLogin(String name, String password) {
		return userRepository.findByLogin(name, password);
	}

	@Override
	public User findId(String name) {
		return null;
	}

	@Override
	public Boolean isAdmin(Long id) {
		try {
			// Buscamos o usuário no banco de dados
			Optional<User> userLogin = userRepository.findById( id );
			
			// Fazemos uma declaração do usuário logado
			User userLogged = null;
			
			//Verificamos se foi achado algum usuário no banco de dados
			if (userLogin.isPresent()) {
				userLogged = userLogin.get();
			} else {
				return null;				
			}
							
			if((userLogin != null) && userLogged.getType().getId() == 3 ) {	
				return true;							
			} else {
				return false;
			}
	
		}catch ( Exception e ) {
			System.out.println("Erro ao validar administrador Error :" + e);
			return false;	
		}
		
		
	}

}
