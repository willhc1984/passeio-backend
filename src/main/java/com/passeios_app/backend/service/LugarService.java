package com.passeios_app.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.passeios_app.backend.exception.RecursoNaoEncontradoException;
import com.passeios_app.backend.model.Lugar;
import com.passeios_app.backend.repository.LugarRepository;

@Service
public class LugarService {
	
	private final LugarRepository repository;
	
	public LugarService(LugarRepository repository) {
		this.repository = repository;
	}
	
	public List<Lugar> listar(){
		return repository.findAll();
	}
	
	public Lugar buscarPorId(Long id) {
		return repository.findById(id)
				.orElseThrow( () -> new RecursoNaoEncontradoException("Lugar não encontrado."));
	}
	
	public Lugar salvar(Lugar lugar) {
		return repository.save(lugar);
	}
	
	public Lugar atualizar(Long id, Lugar lugar) {
		Lugar existente = buscarPorId(id);
		
		existente.setNome(lugar.getNome());
		existente.setLocalizacao(lugar.getLocalizacao());
		existente.setUrlFoto(lugar.getUrlFoto());
		existente.setAvaliacao(lugar.getAvaliacao());
		existente.setCategoria(lugar.getCategoria());
		
		return repository.save(existente);
	}
	
	public void excluir(Long id) {
		if(!repository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Lugar não encontrado.");
		}
		repository.deleteById(id);
	}

}
