package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValidacionSOA extends ResultGenerico {
	private static final long serialVersionUID = 8853532168039145950L;
	
	private Integer validacion;

	public Integer getValidacion() {
		return validacion;
	}

	public void setValidacion(Integer validacion) {
		this.validacion = validacion;
	}
}
