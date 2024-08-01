package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamCalcularCobertura extends ParamGenerico{
	
	private Integer numCotizacion;
	private Integer numCertificado;
	private String codPlanCobertura;
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
	public String getCodPlanCobertura() {
		return codPlanCobertura;
	}
	public void setCodPlanCobertura(String codPlanCobertura) {
		this.codPlanCobertura = codPlanCobertura;
	}

	public Integer getNumBien() {
		return numBien;
	}
	
	public void setNumBien(Integer numBien) {
		this.numBien = numBien;
	}
	
	

}
