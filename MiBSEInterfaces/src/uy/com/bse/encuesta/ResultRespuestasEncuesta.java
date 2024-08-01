package uy.com.bse.encuesta;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultRespuestasEncuesta extends ResultGenerico {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1970363178531273880L;

	private Boolean realizoEncuesta;

	public Boolean getRealizoEncuesta() {
		return realizoEncuesta;
	}

	public void setRealizoEncuesta(Boolean realizoEncuesta) {
		this.realizoEncuesta = realizoEncuesta;
	}
	
}
