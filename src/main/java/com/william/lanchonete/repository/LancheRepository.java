package com.william.lanchonete.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.william.lanchonete.model.Lanche;

public interface LancheRepository extends JpaRepository<Lanche, Long> {

	Optional<Lanche> findByLancheId(long id);
	
	Optional<Lanche> findByNome(String nome);
}
