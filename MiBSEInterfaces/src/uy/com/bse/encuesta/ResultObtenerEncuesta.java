package uy.com.bse.encuesta;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerEncuesta extends ResultGenerico {
	private static final long serialVersionUID = -2614434046170082082L;

	private Encuesta encuesta;

	public Encuesta getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}

	
}
