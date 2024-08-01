package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamListaTodasSecciones;
import uy.com.bse.cotizaciones.lovs.ResultListaTodasSecciones;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTodasSeccionesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaTodasSecciones ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaTodasSecciones((ParamListaTodasSecciones) param);
	}

	private ResultListaTodasSecciones  listaTodasSecciones(ParamListaTodasSecciones param) {
		return (ResultListaTodasSecciones ) checkNull(new CotizacionesPersist().listaTodasSecciones(param));
	}
}
