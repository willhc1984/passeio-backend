package com.passeios_app.backend.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.passeios_app.backend.exception.RecursoNaoEncontradoException;
import com.passeios_app.backend.exception.RegraNegocioException;
import com.passeios_app.backend.model.Categoria;
import com.passeios_app.backend.repository.CategoriaRepository;
import com.passeios_app.backend.repository.LugarRepository;

@Service
public class CategoriaService {
	
	private final CategoriaRepository categoriaRepository;
	private final LugarRepository lugarRepository;
	
	public CategoriaService(CategoriaRepository categoriaRepository, LugarRepository lugarRepository) {
		this.categoriaRepository = categoriaRepository;
		this.lugarRepository = lugarRepository;
	}
	
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	public Categoria buscarPorId(Long id) {
		return categoriaRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada."));
	}
	
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria atualizar(Long id, Categoria categoria) {
		Categoria existente = buscarPorId(id);
		existente.setNome(categoria.getNome());
		existente.setDescricao(categoria.getDescricao());
		
		return categoriaRepository.save(existente);
	}
	
	public void excluir(Long id) {
		if(!categoriaRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Categoria não encontrada.");
		}
		
		if(lugarRepository.existsByCategoriaId(id)) {
			throw new RegraNegocioException("Categoria possui lugares e não pode ser excluída.");
		}
		
		categoriaRepository.deleteById(id);
	}

	
}
