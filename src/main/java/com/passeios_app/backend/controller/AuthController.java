package com.passeios_app.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.passeios_app.backend.dto.LoginErrorDTO;
import com.passeios_app.backend.dto.LoginRequestDTO;
import com.passeios_app.backend.dto.LoginResponseDTO;
import com.passeios_app.backend.security.JwtService;

@RestController
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha()));
			String token = jwtService.gerarToken(dto.getEmail());
			return ResponseEntity.ok(new LoginResponseDTO(token));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginErrorDTO("Usuário ou senha inválidos."));
		}
		
	}
	
}
