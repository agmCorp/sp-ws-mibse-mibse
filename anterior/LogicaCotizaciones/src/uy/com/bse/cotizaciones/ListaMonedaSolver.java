package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamMoneda;
import uy.com.bse.cotizaciones.lovs.ResultListaMoneda;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaMonedaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaMoneda ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaMoneda((ParamMoneda) param);
	}

	private ResultListaMoneda  listaMoneda(ParamMoneda param) {
		return (ResultListaMoneda ) checkNull(new CotizacionesPersist().listaMoneda(param));
	}
}
