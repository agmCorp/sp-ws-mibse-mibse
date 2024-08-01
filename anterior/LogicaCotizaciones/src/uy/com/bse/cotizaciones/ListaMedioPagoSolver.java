package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamMedioPago;
import uy.com.bse.cotizaciones.lovs.ResultListaMedioPago;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaMedioPagoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaMedioPago ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaMedioPago((ParamMedioPago) param);
	}

	private ResultListaMedioPago  listaMedioPago(ParamMedioPago param) {
		return (ResultListaMedioPago ) checkNull(new CotizacionesPersist().listaMedioPago(param));
	}
}
