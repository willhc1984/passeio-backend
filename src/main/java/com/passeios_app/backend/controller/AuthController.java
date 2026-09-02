package com.passeios_app.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.passeios_app.backend.dto.LoginErrorDTO;
import com.passeios_app.backend.dto.LoginRequestDTO;
import com.passeios_app.backend.dto.LoginResponseDTO;
import com.passeios_app.backend.dto.UsuarioMeResponseDTO;
import com.passeios_app.backend.model.Usuario;
import com.passeios_app.backend.repository.UsuarioRepository;
import com.passeios_app.backend.security.JwtService;
import com.passeios_app.backend.security.UsuarioDetails;

@RestController
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UsuarioRepository usuarioRepository) {
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
	
	@GetMapping("/me")
	public ResponseEntity<UsuarioMeResponseDTO> me(Authentication authentication){
			
		UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();
		
		Usuario usuario = usuarioDetails.getUsuario();
		
		List<String> permissoes = usuario.getRole().getPermissoes().stream().map(permissao -> permissao.getCodigo()).toList();
		
		UsuarioMeResponseDTO dto = new UsuarioMeResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getRole().getId(), permissoes);
		
		return ResponseEntity.ok(dto);
	}
	
}
