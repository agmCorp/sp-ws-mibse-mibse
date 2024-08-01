package uy.com.bse.maestro.personas.personas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamDatosModifPersonaJuridica extends ParamGenerico{
	private Integer codPersona;
	private String fechaInicio;
	private Integer codDireccion;
	private Integer codOrganismo;	
	private Integer codActividad;
	
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Integer getCodDireccion() {
		return codDireccion;
	}
	public void setCodDireccion(Integer codDireccion) {
		this.codDireccion = codDireccion;
	}
	public Integer getCodOrganismo() {
		return codOrganismo;
	}
	public void setCodOrganismo(Integer codOrganismo) {
		this.codOrganismo = codOrganismo;
	}	
	public Integer getCodActividad() {
		return codActividad;
	}
	public void setCodActividad(Integer codActividad) {
		this.codActividad = codActividad;
	}	
}
