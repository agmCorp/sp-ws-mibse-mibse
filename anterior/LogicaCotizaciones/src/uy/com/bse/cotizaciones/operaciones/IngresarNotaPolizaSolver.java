package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class IngresarNotaPolizaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultIngresarNotaPoliza ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return ingresarNotaPoliza((ParamIngresarNotaPoliza) param);
	}

	private ResultIngresarNotaPoliza  ingresarNotaPoliza(ParamIngresarNotaPoliza param) {
		return (ResultIngresarNotaPoliza ) checkNull(new CotOperaciones().ingresarNotaPoliza(param));
	}
}
