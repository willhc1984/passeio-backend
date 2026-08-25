package com.passeios_app.backend.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.passeios_app.backend.dto.CategoriaRequestDTO;
import com.passeios_app.backend.dto.CategoriaResponseDTO;
import com.passeios_app.backend.dto.LugarResponseDTO;
import com.passeios_app.backend.exception.RecursoNaoEncontradoException;
import com.passeios_app.backend.exception.RegraNegocioException;
import com.passeios_app.backend.model.Categoria;
import com.passeios_app.backend.model.Lugar;
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
	
	public List<CategoriaResponseDTO> listar(){
		return categoriaRepository.findAll().stream().map(this::converterCategoriaResponseDTO).toList();
	}
	
	public CategoriaResponseDTO buscarPorId(Long id) {
		return categoriaRepository.findById(id).map(this::converterCategoriaResponseDTO)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada."));
	}
	
	public CategoriaResponseDTO salvar(CategoriaRequestDTO dto) {
		Categoria categoria = new Categoria();
		categoria.setNome(dto.getNome());
		categoria.setDescricao(dto.getDescricao());
		
		Categoria salva = categoriaRepository.save(categoria);
		
		return converterCategoriaResponseDTO(salva);
	}
	
	public Categoria buscarEntidadePorId(Long id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada."));
	}
	
	public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {
		Categoria existente = buscarEntidadePorId(id);
		
		existente.setNome(dto.getNome());
		existente.setDescricao(dto.getDescricao());
		
		Categoria atualizada = categoriaRepository.save(existente);
		return converterCategoriaResponseDTO(atualizada);
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
	
	private CategoriaResponseDTO converterCategoriaResponseDTO(Categoria categoria) { 
		CategoriaResponseDTO dto = new CategoriaResponseDTO();
		dto.setId(categoria.getId());
		dto.setNome(categoria.getNome());
		dto.setDescricao(categoria.getDescricao());
		return dto;
	}
	
	private LugarResponseDTO converterLugarResponseDTO(Lugar lugar) {
		LugarResponseDTO dto = new LugarResponseDTO();
		dto.setNome(lugar.getNome());
		dto.setLocalizacao(lugar.getLocalizacao());
		dto.setUrlFoto(lugar.getUrlFoto());
		dto.setAvaliacao(lugar.getAvaliacao());
		return dto;
	}
	
}
