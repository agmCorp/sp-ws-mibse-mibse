package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamCalcularCuotasVehiculo extends ParamGenerico{
	private static final long serialVersionUID = 4513343292887025060L;
	private Integer numCotizacion;
	
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}

}
