package uy.com.bse.guc.interfaces;

import uy.com.bse.utilitario.dato.ResultGenerico;



public class ResultClaveUsada extends ResultGenerico {

	private static final long serialVersionUID = -4206890380138399196L;
	private Boolean usada;
	
	public Boolean getUsada() {
		return usada;
	}
	public void setUsada(Boolean usada) {
		this.usada = usada;
	}
	
	
}
