package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.consultas.ParamDetalleTextoCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultDetalleTextoCotizacion;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerDetalleTextoCotizacionSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultDetalleTextoCotizacion ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return obtenerDetalleTextoCotizacion((ParamDetalleTextoCotizacion) param);
	}

	private ResultDetalleTextoCotizacion  obtenerDetalleTextoCotizacion(ParamDetalleTextoCotizacion param) {
		return (ResultDetalleTextoCotizacion ) checkNull(new CotizacionesPersist().obtenerDetalleTextoCotizacion(param));
	}
}
