package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerCoberturasXCertificado extends ParamGenerico{
	
	private Integer numCotizacion;
	private Integer numCertificado;
	private Integer numBien;
	
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
	public Integer getNumBien() {
		return numBien;
	}
	public void setNumBien(Integer numBien) {
		this.numBien = numBien;
	}	
	
}
