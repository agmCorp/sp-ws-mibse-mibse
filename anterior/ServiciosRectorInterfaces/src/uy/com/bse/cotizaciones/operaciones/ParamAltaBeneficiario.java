package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAltaBeneficiario extends ParamGenerico{
	private String numCotizacion;
	private String numCertificado;
	private String numConsecutivoBien;
	private String tipoDocumento;
	private String numDocumento;
	private String beneficiario;
	private String codParentesco;
	private String fechaNacimiento;
	private String observaciones;
	private String porcentajeParticipacion;
	
	
	public String getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(String numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getNumConsecutivoBien() {
		return numConsecutivoBien;
	}
	public void setNumConsecutivoBien(String numConsecutivoBien) {
		this.numConsecutivoBien = numConsecutivoBien;
	}
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
	public String getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public String getCodParentesco() {
		return codParentesco;
	}
	public void setCodParentesco(String codParentesco) {
		this.codParentesco = codParentesco;
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
	public String getPorcentajeParticipacion() {
		return porcentajeParticipacion;
	}
	public void setPorcentajeParticipacion(String porcentajeParticipacion) {
		this.porcentajeParticipacion = porcentajeParticipacion;
	}
	
}
