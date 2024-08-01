package uy.com.bse.maestro.personas.personas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamDatosPersonaJuridica extends ParamGenerico{
	private String ruc;
	private String razonSocial;
	private Integer codDireccion;
	private String fechaInicio;
	private String codOrganismo;
	private Integer codActividad;
	private Integer codPersona;
	
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public Integer getCodDireccion() {
		return codDireccion;
	}
	public void setCodDireccion(Integer codDireccion) {
		this.codDireccion = codDireccion;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getCodOrganismo() {
		return codOrganismo;
	}
	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}
	public Integer getCodActividad() {
		return codActividad;
	}
	public void setCodActividad(Integer codActividad) {
		this.codActividad = codActividad;
	}	
}
