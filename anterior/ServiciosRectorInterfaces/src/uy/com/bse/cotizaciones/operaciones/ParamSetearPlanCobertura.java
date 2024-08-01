package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamSetearPlanCobertura extends ParamGenerico {

	private Integer numCotizacion;
	private Integer numCertificado;
	private String planCobertura;

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

	public String getPlanCobertura() {
		return planCobertura;
	}

	public void setPlanCobertura(String planCobertura) {
		this.planCobertura = planCobertura;
	}

}
