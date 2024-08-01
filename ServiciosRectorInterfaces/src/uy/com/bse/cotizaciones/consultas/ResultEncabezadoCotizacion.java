package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultEncabezadoCotizacion extends ResultGenerico{
	private EncabezadoCotizacion encabezadoCotizacion;

	public EncabezadoCotizacion getEncabezadoCotizacion() {
		return encabezadoCotizacion;
	}

	public void setEncabezadoCotizacion(EncabezadoCotizacion encabezadoCotizacion) {
		this.encabezadoCotizacion = encabezadoCotizacion;
	}
	
}
