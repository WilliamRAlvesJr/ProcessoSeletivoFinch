package com.william.lanchonete.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.william.lanchonete.model.Ingrediente;
import com.william.lanchonete.model.Lanche;

public class MontaLanche {

	public static Lanche montaLanche(String nomeLanche, Ingrediente... ingredientes) {
		Lanche lanche = new Lanche();
		lanche.setNome(nomeLanche);
		lanche.setPreco(new BigDecimal("0.00"));

		List<Ingrediente> listIngrediente = new ArrayList<>();

		for (Ingrediente ingrediente : ingredientes) {
			listIngrediente.add(ingrediente);
			lanche.setPreco(lanche.getPreco().add(ingrediente.getPreco()));
		}
//		lanche.setListIngrediente(listIngrediente);

		return lanche;
	}
}
