package com.william.lanchonete.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
//@ToString
@Entity
@Table(name = "lanche_ingrediente")
public class LancheIngrediente implements Serializable {

	private static final long serialVersionUID = 5288484848080404870L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lanche_ingrediente_id")
	private long lancheIngredienteId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lanche_id")
    Lanche lanche;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ingrediente_id")
	Ingrediente ingrediente;

	public LancheIngrediente(Lanche lanche, Ingrediente ingrediente) {
		this.lanche = lanche;
		this.ingrediente = ingrediente;
	}
	
	@Override
	public String toString() {
		return "LancheIngrediente [lancheIngredienteId=" + lancheIngredienteId + "]";
	}
}
