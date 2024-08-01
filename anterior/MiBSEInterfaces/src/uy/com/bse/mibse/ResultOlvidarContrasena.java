package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultOlvidarContrasena extends ResultGenerico {
	private static final long serialVersionUID = -566111179456801184L;
	
	private String numCliente;

	public String getNumCliente() {
		return numCliente;
	}

	public void setNumCliente(String numCliente) {
		this.numCliente = numCliente;
	}

}
