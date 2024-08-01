package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamListaTipoNota;
import uy.com.bse.cotizaciones.lovs.ResultListaTipoNota;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTipoNotaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaTipoNota ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaTipoNota((ParamListaTipoNota) param);
	}

	private ResultListaTipoNota  listaTipoNota(ParamListaTipoNota param) {
		return (ResultListaTipoNota ) checkNull(new CotizacionesPersist().listaTipoNota(param));
	}
}
