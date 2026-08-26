package com.passeios_app.backend.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final Key chave = Keys.secretKeyFor(SignatureAlgorithm.HS256);	
	private final long expiracao = 1000 * 60 * 60;  // uma hora
	
	public String gerarToken(String email) {
		Date agora = new Date();
		Date dataExpiracao = new Date(agora.getTime() + expiracao);
		
		return Jwts.builder().subject(email).issuedAt(dataExpiracao).expiration(dataExpiracao).signWith(chave).compact();
	}
	
	public String extrairEmail(String token) {
		return Jwts.parser()
				.verifyWith((javax.crypto.SecretKey) chave)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}

}
