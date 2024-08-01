package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamVigencia;
import uy.com.bse.cotizaciones.lovs.ResultListaVigencia;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaVigenciaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaVigencia ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaVigencia((ParamVigencia) param);
	}

	private ResultListaVigencia  listaVigencia(ParamVigencia param) {
		return (ResultListaVigencia ) checkNull(new CotizacionesPersist().listaVigencia(param));
	}
}
