package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamEnviarCotizacionFueraPauta extends ParamGenerico{
	
	private Integer numCotizacion;

	public Integer getNumCotizacion() {
		return numCotizacion;
	}

	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}	

}
