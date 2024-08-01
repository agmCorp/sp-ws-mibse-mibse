package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValidarCodigoAdhesion extends ResultGenerico {
	private static final long serialVersionUID = -566111179456801184L;
	
	private String validado;

	public final String getValidado() {
		return validado;
	}

	public final void setValidado(String validado) {
		this.validado = validado;
	}

}
