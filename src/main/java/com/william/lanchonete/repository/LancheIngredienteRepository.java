package com.william.lanchonete.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.william.lanchonete.model.LancheIngrediente;

public interface LancheIngredienteRepository extends JpaRepository<LancheIngrediente, Long> {

//	Optional<LancheIngrediente> findByLancheIngredienteId(long id);
	
//	@Query("SELECT coalesce(max(ch.id), 0) FROM lanche_ingrediente ch")
//	Long getMaxId();
}
