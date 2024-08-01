package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamListaBancos;
import uy.com.bse.cotizaciones.lovs.ResultListaBancos;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaBancosSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaBancos ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaBancos((ParamListaBancos) param);
	}

	private ResultListaBancos  listaBancos(ParamListaBancos param) {
		return (ResultListaBancos ) checkNull(new CotizacionesPersist().listaBancos(param));
	}
}
