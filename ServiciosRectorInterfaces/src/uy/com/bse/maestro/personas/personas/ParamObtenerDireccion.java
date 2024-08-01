package uy.com.bse.maestro.personas.personas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerDireccion extends ParamGenerico{
	private Integer codPersona;
	private Integer codDireccion;
	
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}
	public Integer getCodDireccion() {
		return codDireccion;
	}
	public void setCodDireccion(Integer codDireccion) {
		this.codDireccion = codDireccion;
	}	
}
