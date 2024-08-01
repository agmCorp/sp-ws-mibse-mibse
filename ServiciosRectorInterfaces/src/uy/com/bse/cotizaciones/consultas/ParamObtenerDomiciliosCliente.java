package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerDomiciliosCliente extends ParamGenerico{
	public Integer getNumPersona() {
		return numPersona;
	}

	public void setNumPersona(Integer numPersona) {
		this.numPersona = numPersona;
	}

	private Integer numPersona;
	

}
