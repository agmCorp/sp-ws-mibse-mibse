package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDetalleTextoPoliza extends ResultGenerico{
	private String detalleTexto;

	public String getDetalleTexto() {
		return detalleTexto;
	}

	public void setDetalleTexto(String detalleTexto) {
		this.detalleTexto = detalleTexto;
	}
}
