package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamBonificacionNS extends ParamGenerico{
	private Integer numCotizacion;

	public Integer getNumCotizacion() {
		return numCotizacion;
	}

	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
}
