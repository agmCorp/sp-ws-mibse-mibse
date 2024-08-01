package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamGuardarPlanes extends ParamGenerico {
	
	private Integer numCotizacion;
	
	public Integer getNumCotizacion() {
		return numCotizacion;
	}

	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}

	private ArrayList<DatosPlanesCotizacion> planesCotizacion = new ArrayList<DatosPlanesCotizacion>();

	public ArrayList<DatosPlanesCotizacion> getPlanesCotizacion() {
		return planesCotizacion;
	}

	public void setPlanesCotizacion(
			ArrayList<DatosPlanesCotizacion> planesCotizacion) {
		this.planesCotizacion = planesCotizacion;
	}
	
	public void setUnPlan(DatosPlanesCotizacion item){
		this.planesCotizacion.add(item);
	}

}
