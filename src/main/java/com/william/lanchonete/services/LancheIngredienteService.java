package com.william.lanchonete.services;

import java.util.List;
import java.util.Optional;

import com.william.lanchonete.model.LancheIngrediente;

public interface LancheIngredienteService {

	List<LancheIngrediente> buscaTodos();
	Optional<LancheIngrediente> buscarPorId(long id);
	List<Optional<LancheIngrediente>> buscarPorLancheNome(String nome);
	void salvar(LancheIngrediente lancheIngrediente);
	void salvarTodos(LancheIngrediente... lancheIngrediente);
	void salvarListaIngredientes(List<LancheIngrediente> listLancheIngrediente);
	void removeTodos();

}
