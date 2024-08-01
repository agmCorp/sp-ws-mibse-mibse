package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamHabilitoBotones extends ParamGenerico{
	private String numCotizacion;
	private String numCertificado;
	private String codBien;
	private String consecutivoBien;
	
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
	public String getCodBien() {
		return codBien;
	}
	public void setCodBien(String codBien) {
		this.codBien = codBien;
	}
	public String getConsecutivoBien() {
		return consecutivoBien;
	}
	public void setConsecutivoBien(String consecutivoBien) {
		this.consecutivoBien = consecutivoBien;
	}
	
}

