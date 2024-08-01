package uy.com.bse.cotizadorvya.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamRentaMaxima extends ParamGenerico {
	private static final long serialVersionUID = -7042166523245251154L;
	
	private Double ingresoMensual;

	public Double getIngresoMensual() {
		return ingresoMensual;
	}

	public void setIngresoMensual(Double ingresoMensual) {
		this.ingresoMensual = ingresoMensual;
	}
}
