package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDetalleBusquedaCotizacion extends ResultGenerico {

	private static final long serialVersionUID = 1203623462964640129L;
	private Cotizacion cotizacion;

	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

}
