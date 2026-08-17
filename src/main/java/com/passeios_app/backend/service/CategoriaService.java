package com.passeios_app.backend.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.passeios_app.backend.exception.RecursoNaoEncontradoException;
import com.passeios_app.backend.model.Categoria;
import com.passeios_app.backend.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	private final CategoriaRepository repository;
	
	public CategoriaService(CategoriaRepository repository) {
		this.repository = repository;
	}
	
	public List<Categoria> listar(){
		return repository.findAll();
	}
	
	public Categoria buscarPorId(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada."));
	}
	
	public Categoria salvar(Categoria categoria) {
		return repository.save(categoria);
	}
	
	public Categoria atualizar(Long id, Categoria categoria) {
		Categoria existente = buscarPorId(id);
		existente.setNome(categoria.getNome());
		existente.setDescricao(categoria.getDescricao());
		
		return repository.save(existente);
	}
	
	public void excluir(Long id) {
		if(!repository.existsById(id)) {
			throw new RuntimeException("Categoria não encontrada.");
		}
		repository.deleteById(id);
	}

	
}
