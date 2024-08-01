package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamEnPauta;
import uy.com.bse.cotizaciones.lovs.ResultListaEnPauta;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaEnPautaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaEnPauta ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaEnPauta((ParamEnPauta) param);
	}

	private ResultListaEnPauta  listaEnPauta(ParamEnPauta param) {
		return (ResultListaEnPauta ) checkNull(new CotizacionesPersist().listaEnPauta(param));
	}
}
