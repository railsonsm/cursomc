package com.cursomc.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cursomc.models.Categoria;

public class CategoriaDTO {
	private Long id;
	
	@NotBlank(message="Preenchimento obrigatório")
	@Length(min=5,max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
