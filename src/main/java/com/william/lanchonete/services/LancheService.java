package com.william.lanchonete.services;

import java.util.List;
import java.util.Optional;

import com.william.lanchonete.model.Lanche;

public interface LancheService {

	List<Lanche> buscaTodos();
	Optional<Lanche> buscarPorId(long id);
	Optional<Lanche> buscarPorNome(String nome);
	void salvarTodos(Lanche... lanches);
	void salvar(Lanche lanche);
	void removeTodos();
	
}
