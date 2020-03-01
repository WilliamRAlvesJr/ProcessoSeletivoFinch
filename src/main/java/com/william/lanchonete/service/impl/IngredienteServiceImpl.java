package com.william.lanchonete.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.lanchonete.model.Ingrediente;
import com.william.lanchonete.repository.IngredienteRepository;
import com.william.lanchonete.services.IngredienteService;

@Service
public class IngredienteServiceImpl implements IngredienteService {

	private static final Logger log = LoggerFactory.getLogger(IngredienteServiceImpl.class);
	
	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Override
	public List<Ingrediente> buscaTodos() {
		log.info("Buscando por todos os ingredientes");
		return ingredienteRepository.findAll();
	}

	@Override
	public Optional<Ingrediente> buscarPorId(long id) {
		log.info("Buscando por ingrediente com id: {}", id);
		return ingredienteRepository.findByIngredienteId(id);
	}

	@Override
	public Optional<Ingrediente> buscarPorNome(String nome) {
		log.info("Buscando por ingrediente com nome: {}", nome);
		return ingredienteRepository.findByNome(nome);
	}

	@Override
	public void atualizar(Ingrediente ingrediente) {
		log.info("Atualizando o ingrediente: {}", ingrediente);
		this.ingredienteRepository.save(ingrediente);
	}

	@Override
	public void salvar(Ingrediente ingrediente) {
		log.info("Salvando ingrediente: {}", ingrediente);
		ingredienteRepository.save(ingrediente);
	}

	@Override
	public void salvarTodos(Ingrediente... ingredientes) {
		for (Ingrediente ingrediente : ingredientes) {
			salvar(ingrediente);
		}
	}
	
	@Override
	public void salvarListaIngredientes(List<Ingrediente> listIngrediente) {
		log.info("Salvando ingrediente(s): {}", listIngrediente);
		ingredienteRepository.saveAll(listIngrediente);
	}

	@Override
	public void removeTodos() {
		log.info("Deletando todos os registros");
		ingredienteRepository.deleteAll();
		ingredienteRepository.flush();
	}
	
}
