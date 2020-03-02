package com.william.lanchonete.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		lancheServiceImpl.salvar(lanche);
		for (Ingrediente ingrediente : ingredientes) {
			lanche.setPreco(lanche.getPreco().add(ingrediente.getPreco()));
			lancheIngredienteServiceImpl.salvar(new LancheIngrediente(lanche, ingrediente));
		}
		aplicarPromocoes(lanche);
		lancheServiceImpl.salvar(lanche);
	}
	
	@DeleteMapping("/deletaTodos")
	public void removerTodos() {
		lancheIngredienteServiceImpl.removeTodos();
	}
	
	private void aplicarPromocoes(Lanche lanche) {
		List<Optional<LancheIngrediente>> listOptionalLancheIngrediente = 
				lancheIngredienteServiceImpl.buscarPorLancheNome(lanche.getNome());
		
		List<Ingrediente> listIngredientes = new ArrayList<>();
		for (Optional<LancheIngrediente> optionalLancheIngrediente : listOptionalLancheIngrediente) {
			LancheIngrediente lancheIngrediente = optionalLancheIngrediente
				.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o lanche-ingrediente do lanche: " + lanche.getNome()));
			listIngredientes.add(lancheIngrediente.getIngrediente());
		}
		
		if (listIngredientes.stream().anyMatch(str -> str.getNome().equals("alface")) 
			&& !listIngredientes.stream().anyMatch(str -> str.getNome().equals("bacon"))) {
			lanche.setPreco(lanche.getPreco().multiply(new BigDecimal("0.9")));
		}
		
		if (listIngredientes.stream().filter(str -> str.getNome().equals("hamburguer")).count() >= 3) {
			long hamburguerDescontado = (listIngredientes.stream().filter(str -> str.getNome().equals("hamburguer")).count() / 3);
			lanche.setPreco(lanche.getPreco()
				.subtract(
					ingredienteController.buscarPorNome("hamburguer").getPreco()
					.multiply(new BigDecimal(String.valueOf(hamburguerDescontado))
					)
				)
			);
		}
		
		if (listIngredientes.stream().filter(str -> str.getNome().equals("queijo")).count() >= 3) {
			long hamburguerDescontado = (listIngredientes.stream().filter(str -> str.getNome().equals("queijo")).count() / 3);
			lanche.setPreco(lanche.getPreco()
				.subtract(
					ingredienteController.buscarPorNome("queijo").getPreco()
					.multiply(new BigDecimal(String.valueOf(hamburguerDescontado))
					)
				)
			);
		}
	}
}
