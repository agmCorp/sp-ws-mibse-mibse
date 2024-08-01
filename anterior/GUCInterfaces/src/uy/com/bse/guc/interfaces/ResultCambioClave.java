package uy.com.bse.guc.interfaces;

import uy.com.bse.utilitario.dato.ResultGenerico;



public class ResultCambioClave extends ResultGenerico {

	private static final long serialVersionUID = -4206890380138399196L;
	private String mensajeInformacion;
	
	public String getMensajeInformacion() {
		return mensajeInformacion;
	}

	public void setMensajeInformacion(String mensajeInformacion) {
		this.mensajeInformacion = mensajeInformacion;
	}
}
