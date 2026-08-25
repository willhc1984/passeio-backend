package com.passeios_app.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.passeios_app.backend.dto.LugarRequestDTO;
import com.passeios_app.backend.dto.LugarResponseDTO;
import com.passeios_app.backend.exception.RecursoNaoEncontradoException;
import com.passeios_app.backend.model.Categoria;
import com.passeios_app.backend.model.Lugar;
import com.passeios_app.backend.repository.CategoriaRepository;
import com.passeios_app.backend.repository.LugarRepository;

@Service
public class LugarService {
	
	private final LugarRepository repository;
	private final CategoriaRepository categoriaRepository;
	
	public LugarService(LugarRepository repository, CategoriaRepository categoriaRepository) {
		this.repository = repository;
		this.categoriaRepository = categoriaRepository;
	}
	
	public List<LugarResponseDTO> listar(){
		return repository.findAll().stream().map(this::converterLugarResponseDTO).toList();
	}
	
	public LugarResponseDTO buscarPorId(Long id) {
		return repository.findById(id).map(this::converterLugarResponseDTO)
				.orElseThrow( () -> new RecursoNaoEncontradoException("Lugar não encontrado."));
	}
	
	private Lugar buscarEntidadePorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Lugar não encontrado"));
	}
	
	public LugarResponseDTO salvar(LugarRequestDTO dto) {
		Lugar lugar = new Lugar();
		
		lugar.setNome(dto.getNome());
		lugar.setLocalizacao(dto.getLocalizacao());
		lugar.setUrlFoto(dto.getUrlFoto());
		lugar.setAvaliacao(dto.getAvaliacao());
		
		if(dto.getCategoriaId() != null) {
			Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
					.orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada."));
			lugar.setCategoria(categoria);
		}
		
		Lugar salvo = repository.save(lugar);		
		return converterLugarResponseDTO(salvo);		
	}
	
	public LugarResponseDTO atualizar(Long id, LugarRequestDTO dto) {
		Lugar existente = buscarEntidadePorId(id);
		
		existente.setNome(dto.getNome());
		existente.setLocalizacao(dto.getLocalizacao());
		existente.setUrlFoto(dto.getUrlFoto());
		existente.setAvaliacao(dto.getAvaliacao());
		
		if(dto.getCategoriaId() != null) {
			Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
					.orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada"));
			existente.setCategoria(categoria);
		}
		
		Lugar salvo = repository.save(existente);		
		return converterLugarResponseDTO(salvo);
	}
	
	public void excluir(Long id) {
		if(!repository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Lugar não encontrado.");
		}
		repository.deleteById(id);
	}
	
	private LugarResponseDTO converterLugarResponseDTO(Lugar lugar) {
		LugarResponseDTO dto = new LugarResponseDTO();
		dto.setId(lugar.getId());
		dto.setNome(lugar.getNome());
		dto.setLocalizacao(lugar.getLocalizacao());
		dto.setUrlFoto(lugar.getUrlFoto());
		dto.setAvaliacao(lugar.getAvaliacao());
		
		if(lugar.getCategoria() != null) {
			dto.setCategoriaId(lugar.getCategoria().getId());
		}
		
		return dto;
	}

}
