package uy.com.bse.maestro.personas.domicilio;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamDatosBajaDireccion  extends ParamGenerico{
	private Integer codPersona;
	private String usuario;
	private Integer codDireccion;
	
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Integer getCodDireccion() {
		return codDireccion;
	}
	public void setCodDireccion(Integer codDireccion) {
		this.codDireccion = codDireccion;
	}	
}
