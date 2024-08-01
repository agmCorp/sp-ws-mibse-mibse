package uy.com.bse.guc.interfaces;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultOlvidoClave extends ResultGenerico {

	private static final long serialVersionUID = 6405142958317782456L;
	private String mensajeInformacion;

	public String getMensajeInformacion() {
		return mensajeInformacion;
	}

	public void setMensajeInformacion(String mensajeInformacion) {
		this.mensajeInformacion = mensajeInformacion;
	}
}
