package uy.com.bse.maestro.personas.personas;

import java.io.Serializable;

public class PersonaJuridica implements Serializable{
	private Integer codPersona;
	private String razonSocial;
	private String Ruc;	
	private String fechaInicio;
	private String codOrganismo;
	private Integer codActividad;
	private Integer codDireccionHabitual; 
	private String fechaAlta;
	private String fechaBaja;
	private Integer codMotivoBaja; 	
	private String fechaActualizacion;
	private Boolean hayError;
	private String descripDireccionHabitual;
	private String descripActividad;	
	private String descripMotivoBaja;
	private String descripOrganismo;
	private String usuario;
	private String codCliente;
	private String esPEP;
	private String fechaDiligencia;
	
	public String getEsPEP() {
		return esPEP;
	}
	public void setEsPEP(String esPEP) {
		this.esPEP = esPEP;
	}
	public String getFechaDiligencia() {
		return fechaDiligencia;
	}
	public void setFechaDiligencia(String fechaDiligencia) {
		this.fechaDiligencia = fechaDiligencia;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getDescripOrganismo() {
		return descripOrganismo;
	}
	public void setDescripOrganismo(String descripOrganismo) {
		this.descripOrganismo = descripOrganismo;
	}
	public String getDescripMotivoBaja() {
		return descripMotivoBaja;
	}
	public void setDescripMotivoBaja(String descripMotivoBaja) {
		this.descripMotivoBaja = descripMotivoBaja;
	}
	public String getDescripActividad() {
		return descripActividad;
	}
	public void setDescripActividad(String descripActividad) {
		this.descripActividad = descripActividad;
	}
	public String getDescripDireccionHabitual() {
		return descripDireccionHabitual;
	}
	public void setDescripDireccionHabitual(String descripDireccionHabitual) {
		this.descripDireccionHabitual = descripDireccionHabitual;
	}
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRuc() {
		return Ruc;
	}
	public void setRuc(String ruc) {
		Ruc = ruc;
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
	public Integer getCodDireccionHabitual() {
		return codDireccionHabitual;
	}
	public void setCodDireccionHabitual(Integer codDireccionHabitual) {
		this.codDireccionHabitual = codDireccionHabitual;
	}
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public Integer getCodMotivoBaja() {
		return codMotivoBaja;
	}
	public void setCodMotivoBaja(Integer codMotivoBaja) {
		this.codMotivoBaja = codMotivoBaja;
	}
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public Boolean getHayError() {
		return hayError;
	}
	public void setHayError(Boolean hayError) {
		this.hayError = hayError;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	
}
