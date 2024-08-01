package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultGuardarImprimirReporte extends ResultGenerico {

	private static final long serialVersionUID = 240328031030832295L;
	private String numSecuencia;

	public String getNumSecuencia() {
		return numSecuencia;
	}

	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
}
