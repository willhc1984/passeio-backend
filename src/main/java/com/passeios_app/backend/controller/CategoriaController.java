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

import com.passeios_app.backend.model.Categoria;
import com.passeios_app.backend.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	private final CategoriaService service;
	
	public CategoriaController(CategoriaService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Categoria> listar(){
		return service.listar();
	}
		
	@GetMapping("/{id}")
	public Categoria buscarPorId(@PathVariable Long id) {
		return service.buscarPorId(id);
	}
	
	@PostMapping
	public Categoria salvar(@RequestBody Categoria categoria) {
		return service.salvar(categoria);
	}
	
	@PutMapping("/{id}")
	public Categoria atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
		return service.atualizar(id, categoria);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
	
}
