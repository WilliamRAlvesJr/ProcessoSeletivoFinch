package com.william.lanchonete.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ingrediente")
public class Ingrediente implements Serializable {

	private static final long serialVersionUID = 4737250446491992702L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ingredienteId;
	
	@NotNull
	private String nome;
	
	@NotNull
	private BigDecimal preco;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "lanche_ingrediente_id")
	List<LancheIngrediente> listLancheIngrediente;

	public Ingrediente(String nome, BigDecimal preco) {
		this.nome = nome.toLowerCase();
		this.preco = preco;
	}
	
	public void setNome(String nome) {
		this.nome = nome.toLowerCase(); 
	}
}
