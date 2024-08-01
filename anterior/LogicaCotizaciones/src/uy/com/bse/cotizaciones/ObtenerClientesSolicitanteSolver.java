package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.consultas.ParamObtenerClientesSolicitante;
import uy.com.bse.cotizaciones.consultas.ResultObtenerClientesSolicitante;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerClientesSolicitanteSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerClientesSolicitante ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return obtenerClientesSolicitante((ParamObtenerClientesSolicitante) param);
	}

	private ResultObtenerClientesSolicitante  obtenerClientesSolicitante(ParamObtenerClientesSolicitante param) {
		return (ResultObtenerClientesSolicitante ) checkNull(new CotizacionesPersist().obtenerClientesSolicitante(param));
	}
}
