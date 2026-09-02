package com.passeios_app.backend.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final SecretKey chave;
	private final long expiracao = 1000 * 60 * 60;  // uma hora
	
	public JwtService(@Value("${jwt.secret}") String secret) {
		this.chave = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}
	
	public String gerarToken(String email) {
		Date agora = new Date();
		Date dataExpiracao = new Date(agora.getTime() + expiracao);
		
		return Jwts.builder().subject(email).issuedAt(agora).expiration(dataExpiracao).signWith(chave).compact();
	}
	
	public String extrairEmail(String token) {
		return Jwts.parser()
				.verifyWith(chave)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}

}
