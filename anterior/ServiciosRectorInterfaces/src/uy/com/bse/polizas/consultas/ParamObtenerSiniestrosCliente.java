package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerSiniestrosCliente extends ParamGenerico{
	private String codCliente;
	private String nacionalidadCliente;
	
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getNacionalidadCliente() {
		return nacionalidadCliente;
	}
	public void setNacionalidadCliente(String nacionalidadCliente) {
		this.nacionalidadCliente = nacionalidadCliente;
	}
	

}
