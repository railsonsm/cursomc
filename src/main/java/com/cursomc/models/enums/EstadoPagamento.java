package com.cursomc.models.enums;

public enum EstadoPagamento {
	PENDENTE(1,"Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descircao;
	private EstadoPagamento(int cod, String descircao) {
		this.cod = cod;
		this.descircao = descircao;
	}
	public int getCod() {
		return cod;
	}
	public String getDescircao() {
		return descircao;
	}
	
	public static EstadoPagamento toUnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		for (EstadoPagamento ep : EstadoPagamento.values()) {
			if(cod.equals(ep.getCod())) {
				return ep;
			}
		}
		throw new IllegalArgumentException("Id Inválido" + cod);
	}
	
	
}
