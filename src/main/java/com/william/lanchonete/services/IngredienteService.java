package com.william.lanchonete.services;

import java.util.List;
import java.util.Optional;

import com.william.lanchonete.model.Ingrediente;

public interface IngredienteService {

	List<Ingrediente> buscaTodos();
	Optional<Ingrediente> buscarPorId(long id);
	Optional<Ingrediente> buscarPorNome(String nome);
	void atualizar(Ingrediente ingrediente);
	void salvar(Ingrediente ingrediente);
	void salvarTodos(Ingrediente... ingrediente);
	void salvarListaIngredientes(List<Ingrediente> listIngrediente);
	void removeTodos();
}
