package uy.com.bse.encuesta;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerEncuesta extends ParamGenerico {
	

	private static final long serialVersionUID = 1961167762153924393L;
	
	private String idEncuesta;
	public String getIdEncuesta() {
		return idEncuesta;
	}
	public void setIdEncuesta(String idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	
}
