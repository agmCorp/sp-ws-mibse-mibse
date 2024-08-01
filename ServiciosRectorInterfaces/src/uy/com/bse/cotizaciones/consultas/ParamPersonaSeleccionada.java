package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamPersonaSeleccionada extends ParamGenerico{
	
	private Integer numPersona;
	private String tipoCliente;
	
	public Integer getNumPersona() {
		return numPersona;
	}
	public void setNumPersona(Integer numPersona) {
		this.numPersona = numPersona;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
		
}
