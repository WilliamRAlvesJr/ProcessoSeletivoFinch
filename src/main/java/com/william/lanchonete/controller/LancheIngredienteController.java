package com.william.lanchonete.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.lanchonete.model.Ingrediente;
import com.william.lanchonete.model.Lanche;
import com.william.lanchonete.model.LancheIngrediente;
import com.william.lanchonete.service.impl.LancheIngredienteServiceImpl;
import com.william.lanchonete.service.impl.LancheServiceImpl;

@RestController
@RequestMapping("/lancheIngrediente")
public class LancheIngredienteController {

	@Autowired
	LancheIngredienteServiceImpl lancheIngredienteServiceImpl;

	@Autowired
	IngredienteController ingredienteController;
	
	@Autowired
	LancheServiceImpl lancheServiceImpl;
	
	@GetMapping
	public List<LancheIngrediente> buscaTodos() {
		return lancheIngredienteServiceImpl.buscaTodos();
	}

	@GetMapping("/buscaPorId/{id}")
	public LancheIngrediente buscaPorId(long id) {
		return lancheIngredienteServiceImpl.buscarPorId(id)
			.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o lanche-ingrediente com id " + id));
	}

	@PostMapping("/montaLanche")
	public void montaLanche(@RequestBody Lanche lanche, @RequestBody Ingrediente... ingredientes) {
		for (Ingrediente ingrediente : ingredientes) {
			lanche.setPreco(lanche.getPreco().add(ingrediente.getPreco()));
			lancheIngredienteServiceImpl.salvar(new LancheIngrediente(lanche, ingrediente));
		}
		lancheServiceImpl.salvar(lanche);
	}
	
	@DeleteMapping("/deletaTodos")
	public void removerTodos() {
		lancheIngredienteServiceImpl.removeTodos();
	}
	
	private void aplicarPromocao(Lanche lanche) {
		
	}
}
