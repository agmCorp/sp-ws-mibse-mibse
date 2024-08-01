package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultRenovacionManual extends ResultGenerico{
	private String resultado;
	private Integer numCotizacion;
	
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	
}
