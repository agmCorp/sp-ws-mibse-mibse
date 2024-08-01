package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamHabilitoUbicacion extends ParamGenerico{
	private Integer numCotizacion;
	private Integer numCertificado;
	private Integer codBien;
	private Integer consecutivoBien;
	
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
	public Integer getCodBien() {
		return codBien;
	}
	public void setCodBien(Integer codBien) {
		this.codBien = codBien;
	}
	public Integer getConsecutivoBien() {
		return consecutivoBien;
	}
	public void setConsecutivoBien(Integer consecutivoBien) {
		this.consecutivoBien = consecutivoBien;
	} 	
}
