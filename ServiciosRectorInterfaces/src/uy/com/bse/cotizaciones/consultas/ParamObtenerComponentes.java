package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerComponentes extends ParamGenerico{
	private String numCotizacion;
	

	public String getNumCotizacion() {
		return numCotizacion;
	}

	public void setNumCotizacion(String numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	
}
