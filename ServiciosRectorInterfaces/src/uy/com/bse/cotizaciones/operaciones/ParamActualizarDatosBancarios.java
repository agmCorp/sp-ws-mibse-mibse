package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamActualizarDatosBancarios extends ParamGenerico{
	
	private Integer numCotizacion;
	private Integer numDomicilioBanco;
	
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public Integer getNumDomicilioBanco() {
		return numDomicilioBanco;
	}
	public void setNumDomicilioBanco(Integer numDomicilioBanco) {
		this.numDomicilioBanco = numDomicilioBanco;
	}
	
	
}
