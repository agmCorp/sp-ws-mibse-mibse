package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamSecciones;
import uy.com.bse.cotizaciones.lovs.ResultListaSecciones;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaSeccionesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaSecciones ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaSecciones((ParamSecciones) param);
	}

	private ResultListaSecciones  listaSecciones(ParamSecciones param) {
		return (ResultListaSecciones ) checkNull(new CotizacionesPersist().listaSecciones(param));
	}
}
