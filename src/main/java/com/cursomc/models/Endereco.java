package com.cursomc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String Integerradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	
	@ManyToOne
	@JoinColumn(name="cidade_id")
	private Cidade cidade;


	public Endereco(Integer id, String Integerradouro, String numero, String complemento, String bairro, String cep,
			Cliente cliente, Cidade cidade) {
		super();
		this.id = id;
		this.Integerradouro = Integerradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cliente = cliente;
		this.cidade = cidade;
	}
	
	public Endereco() {
	}


	public final Integer getId() {
		return id;
	}


	public final void setId(Integer id) {
		this.id = id;
	}


	public final String getIntegerradouro() {
		return Integerradouro;
	}


	public final void setIntegerradouro(String Integerradouro) {
		this.Integerradouro = Integerradouro;
	}


	public final String getNumero() {
		return numero;
	}


	public final void setNumero(String numero) {
		this.numero = numero;
	}


	public final String getComplemento() {
		return complemento;
	}


	public final void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public final String getBairro() {
		return bairro;
	}


	public final void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public final String getCep() {
		return cep;
	}


	public final void setCep(String cep) {
		this.cep = cep;
	}


	public final Cliente getCliente() {
		return cliente;
	}


	public final void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public Cidade getCidade() {
		return cidade;
	}


	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	
}
