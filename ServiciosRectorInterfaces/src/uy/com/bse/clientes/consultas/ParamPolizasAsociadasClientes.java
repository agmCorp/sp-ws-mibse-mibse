package uy.com.bse.clientes.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamPolizasAsociadasClientes extends ParamGenerico{
	
	private String codCliente;
	private Integer codDireccion;
	
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public Integer getCodDireccion() {
		return codDireccion;
	}
	public void setCodDireccion(Integer codDireccion) {
		this.codDireccion = codDireccion;
	}

}
