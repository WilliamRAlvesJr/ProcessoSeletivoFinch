package com.william.lanchonete.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.william.lanchonete.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

	Optional<Ingrediente> findByIngredienteId(long id);
	
	Optional<Ingrediente> findByNome(String nome);
}
