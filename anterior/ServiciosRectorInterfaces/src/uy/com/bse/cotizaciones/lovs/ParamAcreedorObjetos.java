package uy.com.bse.cotizaciones.lovs;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAcreedorObjetos extends ParamGenerico{
	private String numCotizacion;
	private String numCertificado;
	private String codBienAsegurado;
	
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
	public String getCodBienAsegurado() {
		return codBienAsegurado;
	}
	public void setCodBienAsegurado(String codBienAsegurado) {
		this.codBienAsegurado = codBienAsegurado;
	}	

}
