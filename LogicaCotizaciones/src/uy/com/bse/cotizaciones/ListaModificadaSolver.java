package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamModificada;
import uy.com.bse.cotizaciones.lovs.ResultListaModificada;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaModificadaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaModificada ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaModificada((ParamModificada) param);
	}

	private ResultListaModificada  listaModificada(ParamModificada param) {
		return (ResultListaModificada ) checkNull(new CotizacionesPersist().listaModificada(param));
	}
}
