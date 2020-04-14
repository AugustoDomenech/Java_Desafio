package com.mirante.service;

import java.util.Date;

import com.mirante.apirest.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenService {
	// Define o tempo de expiração do token. no caso 30min
	private static final long expirationTime = 3600000; 
	
	//Segredo de decodificação do token
	private static final String key = "sadlajvssa3d2a65s1dasda21312";
	
	public static String generatetoken(User user) {
		return Jwts.builder()
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject( user.getId().toString() ) // Inserimos o ID do usuário dentro do JWT para consulta-lo de forma facíl depois.
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime  ))
			    .signWith(SignatureAlgorithm.HS256, key)
			    .compact();
	}
	
	public static Claims decodeToken(String token) {
		return Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();
	}
	

}
