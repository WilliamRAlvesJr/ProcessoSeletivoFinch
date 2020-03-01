package com.william.lanchonete.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.lanchonete.model.Lanche;
import com.william.lanchonete.service.impl.LancheServiceImpl;

@RestController
@RequestMapping("/lanches")
public class LancheController {

	@Autowired
	LancheServiceImpl lancheServiceImpl;
	
	@GetMapping
	List<Lanche> buscaTodos() {
		return lancheServiceImpl.buscaTodos();
	}

	@GetMapping("/buscarPorId/{id}")
	public Lanche buscarPorId(@PathVariable long id) {
		return lancheServiceImpl.buscarPorId(id)
			.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o lanche com id " + id));
	}
	
	@GetMapping("/buscarPorNome/{nome}")
	public Lanche buscarPorNome(@PathVariable String nome) {
		return lancheServiceImpl.buscarPorNome(nome)
			.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o lanche " + nome));
	}

	@DeleteMapping("/deletaTodos")
	public void removeTodos() {
		lancheServiceImpl.removeTodos();
	}

}
