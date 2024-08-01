package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerCoberturasXBien extends ParamGenerico{
	
	private Integer numCotizacion;
	private Integer numCertificado;
	private Integer consecutivoBien;
	private String cotizando;
	
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public Integer getConsecutivoBien() {
		return consecutivoBien;
	}
	public void setConsecutivoBien(Integer consecutivoBien) {
		this.consecutivoBien = consecutivoBien;
	}
	public String getCotizando() {
		return cotizando;
	}
	public void setCotizando(String cotizando) {
		this.cotizando = cotizando;
	}	
	
}
