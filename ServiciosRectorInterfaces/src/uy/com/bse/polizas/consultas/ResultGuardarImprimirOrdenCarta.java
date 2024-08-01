package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultGuardarImprimirOrdenCarta extends ResultGenerico {

	private static final long serialVersionUID = -5465228885202448846L;
	private String numSecuencia;

	public String getNumSecuencia() {
		return numSecuencia;
	}

	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
}
