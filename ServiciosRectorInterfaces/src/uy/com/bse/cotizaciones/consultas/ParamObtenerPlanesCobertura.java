package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerPlanesCobertura extends ParamGenerico{
	
	private Integer numCotizacion;
	private Integer numCertificado;
	private String requerido;
	private String requeridoPlanPago;
	
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
	public String getRequerido() {
		return requerido;
	}
	public void setRequerido(String requerido) {
		this.requerido = requerido;
	}
	public String getRequeridoPlanPago() {
		return requeridoPlanPago;
	}
	public void setRequeridoPlanPago(String requeridoPlanPago) {
		this.requeridoPlanPago = requeridoPlanPago;
	}
	
}
