package com.william.lanchonete.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.william.lanchonete.model.LancheIngrediente;

public interface LancheIngredienteRepository extends JpaRepository<LancheIngrediente, Long> {

	Optional<LancheIngrediente> findByLancheIngredienteId(long id);
	List<Optional<LancheIngrediente>> findByLancheNome(String nome);

}
