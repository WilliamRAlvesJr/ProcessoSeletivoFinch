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
import com.william.lanchonete.service.impl.LancheServiceImpl;

@SpringBootApplication
@EnableCaching
public class SistemaLanchoneteApplication {
	
	@Autowired
	private IngredienteServiceImpl IngredienteServiceImpl;
	
	@Autowired
	private IngredienteController ingredienteController;

	@Autowired
	private LancheController lancheController;
	
	@Autowired
	private LancheServiceImpl lancheServiceImpl; 
	
	@Autowired
	private LancheIngredienteController lancheIngredienteController;
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaLanchoneteApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			
			// Ingredientes
			this.IngredienteServiceImpl.removeTodos();
			
			ObjectMapper objectMapper = new ObjectMapper();
			Ingrediente ingrediente = null;
			String json = null;
			
			// Salvando via POST
			json =
				"{\n" + 
				"  \"ingredienteId\": null,\n" + 
				"  \"nome\": \"Alface\",\n" + 
				"  \"preco\": 0.40,\n" + 
				"  \"lancheIngrediente\": []\n" + 
				"}";
			ingrediente = objectMapper.readValue(json, Ingrediente.class);
			ingredienteController.novoIngrediente(ingrediente);

			json = 
				"{\n" + 
				"  \"ingredienteId\": null,\n" + 
				"  \"nome\": \"Bacon\",\n" + 
				"  \"preco\": 2.00,\n" + 
				"  \"lancheIngrediente\": []\n" + 
				"}";
			ingrediente = objectMapper.readValue(json, Ingrediente.class);
			ingredienteController.novoIngrediente(ingrediente);
			
			// Salvando direto no banco
			this.IngredienteServiceImpl.salvarTodos(
				new Ingrediente("Hamburguer", new BigDecimal("3.00")),
				new Ingrediente("Ovo",        new BigDecimal("0.80")),
				new Ingrediente("Queijo",     new BigDecimal("1.50"))
			);

			
			
			
			
			lancheController.removeTodos();
			lancheIngredienteController.removerTodos();
			// Lanches
			
//			json =
//				"{\n" + 
//				"  \"ingrediente\": {\n" + 
//				"    \"ingredienteId\": 2,\n" + 
//				"    \"nome\": \"Bacon\",\n" + 
//				"    \"preco\": 2.00,\n" + 
//				"    \"lancheIngrediente\": []\n" +  
//				"  },\n" + 
//				"  \"lanche\": {\n" + 
//				"    \"lancheId\": 6,\n" + 
//				"    \"nome\": \"x-bacon\",\n" + 
//				"    \"preco\": 0,\n" + 
//				"    \"listLancheIngrediente\": []\n" + 
//				"  },\n" + 
//				"  \"lancheIngredienteId\": 0\n" + 
//				"}";
//			LancheIngrediente lancheIngrediente = objectMapper.readValue(json, LancheIngrediente.class);
			
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
			lancheServiceImpl.salvar(lanche);
			lancheIngredienteController.montaLanche(
				lanche, 
				ingredienteController.buscarPorNome("bacon"),
				ingredienteController.buscarPorNome("hamburguer"),
				ingredienteController.buscarPorNome("queijo")
			);
			
			lanche = objectMapper.readValue(json, Lanche.class);
			lanche.setNome("x-burger");
			lancheServiceImpl.salvar(lanche);
			lancheIngredienteController.montaLanche(
				lanche, 
				ingredienteController.buscarPorNome("hamburguer"),
				ingredienteController.buscarPorNome("queijo")
			);
				
			lanche = objectMapper.readValue(json, Lanche.class);
			lanche.setNome("x-egg");
			lancheServiceImpl.salvar(lanche);
			lancheIngredienteController.montaLanche(
				lanche, 
				ingredienteController.buscarPorNome("ovo"),
				ingredienteController.buscarPorNome("hamburguer"),
				ingredienteController.buscarPorNome("queijo")
			);	
				
			lanche = objectMapper.readValue(json, Lanche.class);
			lanche.setNome("x-egg bacon");
			lancheServiceImpl.salvar(lanche);
			lancheIngredienteController.montaLanche(
				lanche, 
				ingredienteController.buscarPorNome("ovo"),
				ingredienteController.buscarPorNome("bacon"),
				ingredienteController.buscarPorNome("hamburguer"),
				ingredienteController.buscarPorNome("queijo")
			);	
		};
	}

}
