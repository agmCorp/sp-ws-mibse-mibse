package uy.com.bse.encuesta;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamGrabarEncuesta extends ParamGenerico {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6134544573093022097L;
	
	private Encuesta encuesta;
	
	public Encuesta getEncuesta() {
		return encuesta;
	}
	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}

}
