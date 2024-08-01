package uy.com.bse.servicios.rector.interfaces;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultTipoUsuario extends ResultGenerico{

	private static final long serialVersionUID = 3027873597674318350L;
	
	private String tipo;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
