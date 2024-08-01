package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamPromocion;
import uy.com.bse.cotizaciones.lovs.ResultListaPromocion;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaPromocionSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaPromocion ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaPromocion((ParamPromocion) param);
	}

	private ResultListaPromocion  listaPromocion(ParamPromocion param) {
		return (ResultListaPromocion ) checkNull(new CotizacionesPersist().listaPromocion(param));
	}
}
