package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamBajaUbicacionBien extends ParamGenerico {
	private String numCotizacion;
	private String numCertificado;
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
	public String getConsecutivoBien() {
		return consecutivoBien;
	}
	public void setConsecutivoBien(String consecutivoBien) {
		this.consecutivoBien = consecutivoBien;
	}
	
	
	public ParamBajaUbicacionBien(String numCotizacion, String numCertificado,String consecutivoBien) {
		super();
		this.numCotizacion = numCotizacion;
		this.numCertificado = numCertificado;
		this.consecutivoBien = consecutivoBien;
	}
	public ParamBajaUbicacionBien() {
		super();
		
	}
	
}	