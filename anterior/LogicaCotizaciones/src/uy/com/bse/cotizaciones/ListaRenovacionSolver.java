package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamRenovacion;
import uy.com.bse.cotizaciones.lovs.ResultListaRenovacion;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaRenovacionSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaRenovacion ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaRenovacion((ParamRenovacion) param);
	}

	private ResultListaRenovacion  listaRenovacion(ParamRenovacion param) {
		return (ResultListaRenovacion ) checkNull(new CotizacionesPersist().listaRenovacion(param));
	}
}
