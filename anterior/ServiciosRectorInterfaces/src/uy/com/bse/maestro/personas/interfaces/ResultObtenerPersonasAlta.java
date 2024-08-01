package uy.com.bse.maestro.personas.interfaces;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPersonasAlta extends ResultGenerico{
	private	Integer numPersona;
	private String codCliente;
	
	public Integer getNumPersona() {
		return numPersona;
	}
	public void setNumPersona(Integer numPersona) {
		this.numPersona = numPersona;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}	

}
