package uy.com.bse.servicios.rector.emision;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValidarCotizacion extends ResultGenerico{
	private ValidaCotizacion validar;

	public ValidaCotizacion getValidar() {
		return validar;
	}

	public void setValidar(ValidaCotizacion validar) {
		this.validar = validar;
	}
}
