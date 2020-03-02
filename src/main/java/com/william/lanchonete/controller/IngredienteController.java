package com.william.lanchonete.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.lanchonete.model.Ingrediente;
import com.william.lanchonete.service.impl.IngredienteServiceImpl;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {

	@Autowired
	private IngredienteServiceImpl ingredienteServiceImpl;

	IngredienteController(IngredienteServiceImpl ingredienteServiceImpl) {
		this.ingredienteServiceImpl = ingredienteServiceImpl;
	}

	@GetMapping
	List<Ingrediente> buscaTodos() {
		return ingredienteServiceImpl.buscaTodos();
	}
	
	@GetMapping("/buscarPorId/{id}")
	Ingrediente buscarPorId(@PathVariable long id) {
		return ingredienteServiceImpl.buscarPorId(id)
			.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o ingrediente com id " + id));
	}

	@CachePut("ingredientePorNome")
	@GetMapping("/buscarPorNome/{nome}")
	public Ingrediente buscarPorNome(@PathVariable String nome) {
		return ingredienteServiceImpl.buscarPorNome(nome)
			.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o ingrediente " + nome));
	}

	@PutMapping("/atualizar/{id}")
	void atualizaIngrediente(@PathVariable long id, @RequestBody String novoValor) {
		Ingrediente ingrediente = buscarPorId(id);
		ingrediente.setPreco(new BigDecimal(novoValor));
		ingredienteServiceImpl.atualizar(ingrediente);
	}
	
	@PostMapping("/novoIngrediente")
	public void novoIngrediente(@RequestBody Ingrediente novoIngrediente) {
		ingredienteServiceImpl.salvar(novoIngrediente);
	}
}
