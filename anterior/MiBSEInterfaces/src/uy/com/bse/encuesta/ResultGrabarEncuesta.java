package uy.com.bse.encuesta;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultGrabarEncuesta extends ResultGenerico {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8847744887593172214L;
	
	private Encuesta encuesta;

	public Encuesta getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}

	
}
