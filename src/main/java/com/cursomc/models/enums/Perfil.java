package com.cursomc.models.enums;

public enum Perfil {
	ADMIN(1,"ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descircao;
	private Perfil(int cod, String descircao) {
		this.cod = cod;
		this.descircao = descircao;
	}
	public int getCod() {
		return cod;
	}
	public String getDescircao() {
		return descircao;
	}
	
	public static Perfil toUnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		for (Perfil ep : Perfil.values()) {
			if(cod.equals(ep.getCod())) {
				return ep;
			}
		}
		throw new IllegalArgumentException("Id Inválido" + cod);
	}
	
	
}
