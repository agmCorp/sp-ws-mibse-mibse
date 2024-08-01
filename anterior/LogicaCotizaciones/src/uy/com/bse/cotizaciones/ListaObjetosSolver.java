package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamObjetos;
import uy.com.bse.cotizaciones.lovs.ResultListaObjetos;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaObjetosSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaObjetos ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaObjetos((ParamObjetos) param);
	}

	private ResultListaObjetos  listaObjetos(ParamObjetos param) {
		return (ResultListaObjetos ) checkNull(new CotizacionesPersist().listaObjetos(param));
	}
}
