package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamBajaBeneficiario extends ParamGenerico{
	private String numCotizacion;
	private String numCertificado;
	private String numConsecutivoBien;
	private String tipoDocumentoReferente;
	private String numDocumentoReferente;
	private String fechaExclusion;
	
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
	public String getTipoDocumentoReferente() {
		return tipoDocumentoReferente;
	}
	public void setTipoDocumentoReferente(String tipoDocumentoReferente) {
		this.tipoDocumentoReferente = tipoDocumentoReferente;
	}
	public String getNumDocumentoReferente() {
		return numDocumentoReferente;
	}
	public void setNumDocumentoReferente(String numDocumentoReferente) {
		this.numDocumentoReferente = numDocumentoReferente;
	}
	public String getFechaExclusion() {
		return fechaExclusion;
	}
	public void setFechaExclusion(String fechaExclusion) {
		this.fechaExclusion = fechaExclusion;
	}


}
