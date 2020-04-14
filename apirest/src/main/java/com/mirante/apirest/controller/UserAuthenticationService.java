package com.mirante.apirest.controller;

import java.util.Date;

import javax.security.auth.message.MessagePolicy.Target;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirante.apirest.model.User;
import com.mirante.apirest.repository.UserRepository;
import com.mirante.service.TokenService;

import io.jsonwebtoken.Claims;

@Service
public class UserAuthenticationService {
	
	public static String authenticate(User user) {
		String token = TokenService.generatetoken(user);
		user.setToken(token);
		return token;
	}
	
	public static boolean validateToken(String token) {
		try {
			String tokenTratado = token.replace("Bearer ", "");
			System.out.println(tokenTratado);
			
			Claims clamis = TokenService.decodeToken(tokenTratado);
			System.out.println("2");
			System.out.println( clamis.getSubject());
			System.out.println(clamis.getIssuer());
			System.out.println(clamis.getIssuedAt());
			
			//Verifica se o token já está expirado
			if ( clamis.getExpiration().before( new Date(System.currentTimeMillis()))) {
				System.out.println("Venvido");
			}	
			return true;
		} catch (Exception e) {
            System.out.println(e.getMessage());
			return false;
		}
	}
	
	// No corpo do Token só é armazenado o ID do usário
	public static Long getIdBodyToken(String token) {
		try {
			String tokenTratado = token.replace("Bearer ", "");
			System.out.println(tokenTratado);
			
			Claims clamis = TokenService.decodeToken(tokenTratado);
			return Long.parseLong(clamis.getSubject());
			
		} catch (Exception e) {
            System.out.println(e.getMessage());
			return -1L;
		}
	}
	
	
}
