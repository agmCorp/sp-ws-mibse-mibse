package uy.com.bse.cotizaciones.entidades;

import java.io.Serializable;



public class Beneficiario implements Serializable{
	
	private String tipoDocumento;
	private String numDocumento;
	private String nombreBeneficiario;
	private String codParentesco;
	private String descripcionParentesco;
	private Double porcentajeParticipacion;
	private String fechaInclusion;
	private String fechaNacimiento;
	private String observaciones;
	private String fechaExclusion;
	private Integer indice;
	private String esNuevo;
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}
	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}
	public String getCodParentesco() {
		return codParentesco;
	}
	public void setCodParentesco(String codParentesco) {
		this.codParentesco = codParentesco;
	}
	public String getDescripcionParentesco() {
		return descripcionParentesco;
	}
	public void setDescripcionParentesco(String descripcionParentesco) {
		this.descripcionParentesco = descripcionParentesco;
	}
	public Double getPorcentajeParticipacion() {
		return porcentajeParticipacion;
	}
	public void setPorcentajeParticipacion(Double porcentajeParticipacion) {
		this.porcentajeParticipacion = porcentajeParticipacion;
	}
	public String getFechaInclusion() {
		return fechaInclusion;
	}
	public void setFechaInclusion(String fechaInclusion) {
		this.fechaInclusion = fechaInclusion;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getFechaExclusion() {
		return fechaExclusion;
	}
	public void setFechaExclusion(String fechaExclusion) {
		this.fechaExclusion = fechaExclusion;
	}
	public Integer getIndice() {
		return indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	public String getEsNuevo() {
		return esNuevo;
	}
	public void setEsNuevo(String esNuevo) {
		this.esNuevo = esNuevo;
	}
	
	

}