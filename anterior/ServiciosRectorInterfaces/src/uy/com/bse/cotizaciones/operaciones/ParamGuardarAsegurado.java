package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamGuardarAsegurado extends ParamGenerico{
	
	private Integer numCotizacion;
	private Integer numCertificado;
	private Integer codCliente;
	
	
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
	public Integer getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}			

}
