package uy.com.bse.utilitario.seguridad;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerUsuario extends ResultGenerico {

	private static final long serialVersionUID = 1L;
	
	private String usuario;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	

}
