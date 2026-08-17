package com.passeios_app.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.passeios_app.backend.model.Lugar;
import com.passeios_app.backend.service.LugarService;

@RestController
@RequestMapping("/lugares")
public class LugarController {
	
	private final LugarService service;
	
	public LugarController(LugarService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Lugar> listar(){
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public Lugar buscarPorId(@PathVariable Long id) {
		return service.buscarPorId(id);
	}
	
	@PostMapping
	public Lugar salvar(@RequestBody Lugar lugar) {
		return service.salvar(lugar);
	}
	
	@PutMapping("/{id}")
	public Lugar atualizar(@PathVariable Long id, @RequestBody Lugar lugar) {
		return service.atualizar(id, lugar);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
	
}
