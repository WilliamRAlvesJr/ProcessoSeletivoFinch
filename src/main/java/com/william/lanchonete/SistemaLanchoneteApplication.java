package com.william.lanchonete;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.william.lanchonete.controller.IngredienteController;
import com.william.lanchonete.controller.LancheController;
import com.william.lanchonete.controller.LancheIngredienteController;
import com.william.lanchonete.model.Ingrediente;
import com.william.lanchonete.model.Lanche;
import com.william.lanchonete.service.impl.IngredienteServiceImpl;

@SpringBootApplication
@EnableCaching
public class SistemaLanchoneteApplication {
	
	@Autowired
	private IngredienteServiceImpl ingredienteServiceImpl;
	
	@Autowired
	private IngredienteController ingredienteController;

	@Autowired
	private LancheController lancheController;
	
	@Autowired
	private LancheIngredienteController lancheIngredienteController;
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaLanchoneteApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			
			// Ingredientes
			this.ingredienteServiceImpl.removeTodos();
			
			ObjectMapper objectMapper = new ObjectMapper();
			Ingrediente ingrediente = null;
			String json = null;
			
			// Salvando via POST
			json =
				"{\n" + 
				"  \"ingredienteId\": null,\n" + 
				"  \"nome\": \"Alface\",\n" + 
				"  \"preco\": 0.40,\n" + 
				"  \"listLancheIngrediente\": []\n" + 
				"}";
			ingrediente = objectMapper.readValue(json, Ingrediente.class);
			ingredienteController.novoIngrediente(ingrediente);

			json = 
				"{\n" + 
				"  \"ingredienteId\": null,\n" + 
				"  \"nome\": \"Bacon\",\n" + 
				"  \"preco\": 2.00,\n" + 
				"  \"listLancheIngrediente\": []\n" + 
				"}";
			ingrediente = objectMapper.readValue(json, Ingrediente.class);
			ingredienteController.novoIngrediente(ingrediente);
			
			// Salvando direto no banco
			this.ingredienteServiceImpl.salvarTodos(
				new Ingrediente("Hamburguer", new BigDecimal("3.00")),
				new Ingrediente("Ovo",        new BigDecimal("0.80")),
				new Ingrediente("Queijo",     new BigDecimal("1.50"))
			);

			
			
			
			
			lancheController.removeTodos();
			lancheIngredienteController.removerTodos();
			// Lanches
			
			Lanche lanche = null;
			json =
				"  {\n" + 
				"    \"lancheId\": null,\n" + 
				"    \"nome\": \"nomeDoLanche\",\n" + 
				"    \"preco\": 0,\n" + 
				"    \"listLancheIngrediente\": []\n" + 
				"  }";
			lanche = objectMapper.readValue(json, Lanche.class);
			lanche.setNome("x-bacon");
			lancheIngredienteController.montaLanche(
				lanche, 
				ingredienteController.buscarPorNome("bacon"),
				ingredienteController.buscarPorNome("hamburguer"),
				ingredienteController.buscarPorNome("queijo")
			);
			
			lanche = objectMapper.readValue(json, Lanche.class);
			lanche.setNome("x-burger");
			lancheIngredienteController.montaLanche(
				lanche, 
				ingredienteController.buscarPorNome("hamburguer"),
				ingredienteController.buscarPorNome("queijo")
			);
				
			lanche = objectMapper.readValue(json, Lanche.class);
			lanche.setNome("x-egg");
			lancheIngredienteController.montaLanche(
				lanche, 
				ingredienteController.buscarPorNome("ovo"),
				ingredienteController.buscarPorNome("hamburguer"),
				ingredienteController.buscarPorNome("queijo")
			);	
				
			lanche = objectMapper.readValue(json, Lanche.class);
			lanche.setNome("x-egg bacon");
			lancheIngredienteController.montaLanche(
				lanche, 
				ingredienteController.buscarPorNome("ovo"),
				ingredienteController.buscarPorNome("bacon"),
				ingredienteController.buscarPorNome("hamburguer"),
				ingredienteController.buscarPorNome("queijo")
			);	
			
			// Lanches promocionais	
			lanche = objectMapper.readValue(json, Lanche.class);
			lanche.setNome("x-salada");
			lancheIngredienteController.montaLanche(
				lanche, 
				ingredienteController.buscarPorNome("alface"),
				ingredienteController.buscarPorNome("hamburguer")
			);
			
			lanche = objectMapper.readValue(json, Lanche.class);
			lanche.setNome("tiplo-burguer");
			lancheIngredienteController.montaLanche(
				lanche,
				ingredienteController.buscarPorNome("hamburguer"),
				ingredienteController.buscarPorNome("hamburguer"),
				ingredienteController.buscarPorNome("hamburguer"),
				ingredienteController.buscarPorNome("queijo")
			);
			
			lanche = objectMapper.readValue(json, Lanche.class);
			lanche.setNome("tiplo-queijo");
			lancheIngredienteController.montaLanche(
				lanche,
				ingredienteController.buscarPorNome("hamburguer"),
				ingredienteController.buscarPorNome("queijo"),
				ingredienteController.buscarPorNome("queijo"),
				ingredienteController.buscarPorNome("queijo")
			);
		};
	}

}
