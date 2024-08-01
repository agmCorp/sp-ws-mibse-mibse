package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamActualizarPromocionVehiculo extends ParamGenerico{
	private static final long serialVersionUID = 4513343292887025060L;
	private Integer numCotizacion;
	private String codPromocion;
	
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getCodPromocion() {
		return codPromocion;
	}
	public void setCodPromocion(String codPromocion) {
		this.codPromocion = codPromocion;
	}
	
}
