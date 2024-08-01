package uy.com.bse.maestro.personas.rol;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamDatosRolCliente extends ParamGenerico{
	private Integer codPersona;	
	private String codSucursalPago;
	private String codCliente;
	
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}			
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getCodSucursalPago() {
		return codSucursalPago;
	}
	public void setCodSucursalPago(String codSucursalPago) {
		this.codSucursalPago = codSucursalPago;
	}	
}
