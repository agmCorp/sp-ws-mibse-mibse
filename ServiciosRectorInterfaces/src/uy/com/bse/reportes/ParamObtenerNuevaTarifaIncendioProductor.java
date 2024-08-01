package uy.com.bse.reportes;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerNuevaTarifaIncendioProductor extends ParamGenerico {

	private static final long serialVersionUID = -4247778112427071528L;
	private String mesAnio;

	
	public String getMesAnio() {
		return mesAnio;
	}
	public void setMesAnio(String mesAnio) {
		this.mesAnio = mesAnio;
	}
	
	
}
