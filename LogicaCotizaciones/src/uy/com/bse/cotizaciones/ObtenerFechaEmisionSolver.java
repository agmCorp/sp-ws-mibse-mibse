package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.consultas.ParamObtenerFechaEmision;
import uy.com.bse.cotizaciones.consultas.ResultObtenerFechaEmision;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerFechaEmisionSolver extends AbstractSolver{
	
	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerFechaEmision ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return obtenerFechaEmision((ParamObtenerFechaEmision) param);
	}

	private ResultObtenerFechaEmision  obtenerFechaEmision(ParamObtenerFechaEmision param) {
		return (ResultObtenerFechaEmision) checkNull(new CotizacionesPersist().obtenerFechaEmision(param));
	}
}
