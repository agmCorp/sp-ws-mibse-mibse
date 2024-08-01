package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamTipo;
import uy.com.bse.cotizaciones.lovs.ResultListaTipo;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTipoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaTipo ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaTipo((ParamTipo) param);
	}

	private ResultListaTipo  listaTipo(ParamTipo param) {
		return (ResultListaTipo ) checkNull(new CotizacionesPersist().listaTipo(param));
	}
}
