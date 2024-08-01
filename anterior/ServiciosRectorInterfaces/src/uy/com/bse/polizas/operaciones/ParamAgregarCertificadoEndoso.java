package uy.com.bse.polizas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAgregarCertificadoEndoso extends ParamGenerico{
	
	private Integer numCotizacion;
	private Integer numCertificado;
	private Integer numPoliza;
	
	
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
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	
	
	

}
