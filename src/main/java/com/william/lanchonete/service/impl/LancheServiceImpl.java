package com.william.lanchonete.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.lanchonete.model.Lanche;
import com.william.lanchonete.repository.LancheRepository;
import com.william.lanchonete.services.LancheService;

@Service
public class LancheServiceImpl implements LancheService {

	private static final Logger log = LoggerFactory.getLogger(LancheServiceImpl.class);
	
	@Autowired
	private LancheRepository lancheRepository;

	@Override
	public List<Lanche> buscaTodos() {
		log.info("Buscando por todos os lanches");
		return lancheRepository.findAll();
	}

	@Override
	public Optional<Lanche> buscarPorId(long id) {
		log.info("Buscando por lanche com id: {}", id);
		return lancheRepository.findById(id);
	}

	@Override
	public Optional<Lanche> buscarPorNome(String nome) {
		log.info("Buscando por lanche com nome: {}", nome);
		return lancheRepository.findByNome(nome);
	}

	@Override
	public void salvar(Lanche lanche) {
		log.info("Salvando lanche: {}", lanche);
		lancheRepository.save(lanche);
	}
	
	@Override
	public void salvarTodos(Lanche... lanches) {
		for (Lanche lanche : lanches) {
			salvar(lanche);
		}
	}

	@Override
	public void removeTodos() {
		log.info("Removendo todos os registros");
		lancheRepository.deleteAll();
	}
}
