package com.cursomc.dto;

import com.cursomc.models.Categoria;

public class CategoriaDTO {
	private Long id;
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
