package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamDatosCertificados extends ParamGenerico{
	private Integer numCotizacion;
	private Integer numCertificado;	
	
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
	
}
