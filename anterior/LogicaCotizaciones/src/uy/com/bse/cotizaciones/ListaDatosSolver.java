package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamDatos;
import uy.com.bse.cotizaciones.lovs.ResultDatos;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaDatosSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultDatos ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaDatos((ParamDatos) param);
	}

	private ResultDatos  listaDatos(ParamDatos param) {
		return (ResultDatos) checkNull(new CotizacionesPersist().listaDatos(param));
	}
}
