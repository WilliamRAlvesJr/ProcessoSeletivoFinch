package com.william.lanchonete.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.lanchonete.model.LancheIngrediente;
import com.william.lanchonete.repository.LancheIngredienteRepository;
import com.william.lanchonete.services.LancheIngredienteService;

@Service
public class LancheIngredienteServiceImpl implements LancheIngredienteService {

	private static final Logger log = LoggerFactory.getLogger(LancheIngredienteServiceImpl.class);
	
	@Autowired
	private LancheIngredienteRepository lancheIngredienteRepository;

	@Override
	public List<LancheIngrediente> buscaTodos() {
		log.info("Buscando por todos os ingredientes");
		return lancheIngredienteRepository.findAll();
	}

	@Override
	public Optional<LancheIngrediente> buscarPorId(long id) {
		log.info("Buscando por ingrediente com id: {}", id);
//		return lancheIngredienteRepository.findByLancheIngredienteId(id);
		return null;
	}

//	@Override
//	public Optional<LancheIngrediente> buscarPorNome(String nome) {
//		log.info("Buscando por ingrediente com nome: {}", nome);
//		return lancheIngredienteRepository.findByNome(nome);
//	}

	@Override
	public void salvar(LancheIngrediente lancheIngrediente) {
		log.info("Salvando lanche-ingrediente: {}", lancheIngrediente);
		lancheIngredienteRepository.save(lancheIngrediente);
	}

	@Override
	public void salvarTodos(LancheIngrediente... lancheIngredientes) {
		for (LancheIngrediente lancheIngrediente : lancheIngredientes) {
			salvar(lancheIngrediente);
		}
	}
	
	@Override
	public void salvarListaIngredientes(List<LancheIngrediente> listLancheIngrediente) {
		log.info("Salvando ingrediente(s): {}", listLancheIngrediente);
		lancheIngredienteRepository.saveAll(listLancheIngrediente);
	}

	@Override
	public void removeTodos() {
		log.info("Deletando todos os registros");
		lancheIngredienteRepository.deleteAll();
		lancheIngredienteRepository.flush();
	}

}
