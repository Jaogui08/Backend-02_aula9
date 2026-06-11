package com.projetologinjoaogui.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produtos")
public class Produtos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	@Size(min = 2, max = 255)
	private String nome;
	
	@NotNull
	@NotBlank
	@Size(min = 2, max = 255)
	private String descricao;
	
	@NotNull
	private Double preco;
	
	@NotNull
	@NotBlank
	@Size(min = 2, max = 255)
	private String url;
	
	@OneToOne(mappedBy = "produtos", cascade = CascadeType.ALL)
	private Estoque estoque;
}
