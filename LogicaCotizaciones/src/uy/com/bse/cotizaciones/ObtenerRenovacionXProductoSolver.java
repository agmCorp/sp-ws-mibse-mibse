package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamRenovacionXProducto;
import uy.com.bse.cotizaciones.lovs.ResultRenovacionXProducto;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerRenovacionXProductoSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return obtenerRenovacionXProducto((ParamRenovacionXProducto) param);
	}

	private ResultRenovacionXProducto obtenerRenovacionXProducto(ParamRenovacionXProducto param) {
		return (ResultRenovacionXProducto) checkNull(new CotizacionesPersist().obtenerRenovacionXProducto(param));
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultRenovacionXProducto();
	}

}
