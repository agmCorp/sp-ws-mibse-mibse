package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultCorrespondeCartaPoliza extends ResultGenerico {
	private static final long serialVersionUID = -7946872889922393075L;
	
	private Boolean correspondeCartaPoliza;
	private String mensaje;

	public Boolean getCorrespondeCartaPoliza() {
		return correspondeCartaPoliza;
	}

	public void setCorrespondeCartaPoliza(Boolean correspondeCartaPoliza) {
		this.correspondeCartaPoliza = correspondeCartaPoliza;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
