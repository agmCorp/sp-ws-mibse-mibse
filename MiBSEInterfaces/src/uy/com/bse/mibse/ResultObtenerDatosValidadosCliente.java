package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosValidadosCliente extends ResultGenerico {
	private static final long serialVersionUID = -566111179456801184L;
	
	private String validado;

	public String getValidado() {
		return validado;
	}

	public void setValidado(String validado) {
		this.validado = validado;
	}

	
	
}
