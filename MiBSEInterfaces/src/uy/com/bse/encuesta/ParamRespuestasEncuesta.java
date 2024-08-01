package uy.com.bse.encuesta;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamRespuestasEncuesta extends ParamGenerico {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3332688010425236039L;

	private String idEncuesta;
	
	private String idSistema;

	public String getIdEncuesta() {
		return idEncuesta;
	}

	public void setIdEncuesta(String idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}
	


}
