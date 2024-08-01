package uy.com.bse.maestro.personas.personas;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultAltaRolCliente extends ResultGenerico{
	private String codCliente;

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	
}
