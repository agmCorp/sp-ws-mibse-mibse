package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamOrigenPago;
import uy.com.bse.cotizaciones.lovs.ResultListaOrigenPago;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaOrigenPagoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaOrigenPago ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaOrigenPago((ParamOrigenPago) param);
	}

	private ResultListaOrigenPago  listaOrigenPago(ParamOrigenPago param) {
		return (ResultListaOrigenPago ) checkNull(new CotizacionesPersist().listaOrigenPago(param));
	}
}
