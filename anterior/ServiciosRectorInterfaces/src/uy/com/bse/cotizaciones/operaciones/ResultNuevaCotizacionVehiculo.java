package uy.com.bse.cotizaciones.operaciones;


import uy.com.bse.cotizaciones.entidades.DatosCotizacionVehiculo;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultNuevaCotizacionVehiculo extends ResultGenerico{

	private static final long serialVersionUID = 5526379621999111510L;
	private DatosCotizacionVehiculo datosCotizacion;

	public DatosCotizacionVehiculo getDatosCotizacion() {
		return datosCotizacion;
	}

	public void setDatosCotizacion(DatosCotizacionVehiculo datosCotizacion) {
		this.datosCotizacion = datosCotizacion;
	}
	

}
